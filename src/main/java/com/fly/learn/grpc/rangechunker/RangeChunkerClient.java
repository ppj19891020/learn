package com.fly.learn.grpc.rangechunker;

import com.demo.grpc.rangechunker.FileSizeRequest;
import com.demo.grpc.rangechunker.FileSizeResponse;
import com.demo.grpc.rangechunker.RangeChunkResponse;
import com.demo.grpc.rangechunker.RangeChunkerGrpc;
import com.demo.grpc.rangechunker.RangeRegionRequest;
import com.demo.grpc.rangechunker.RangeRegionResponse;
import com.demo.grpc.rangechunker.RangeRequest;
import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: peijiepang
 * @date 2020/10/19
 * @Description:
 */
public class RangeChunkerClient {
    private final static Logger LOGGER = LoggerFactory.getLogger(RangeChunkerClient.class);
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8888;

    private ExecutorService executorService = Executors.newFixedThreadPool(16);

    public static void main(String[] args){
        long start = System.currentTimeMillis();
        try {
            RangeChunkerClient client = new RangeChunkerClient(HOST, PORT);
//            client.download("/Users/peijiepang/Downloads/ideaIU-2020.1.1.dmg");
//            client.batchDownload("/Users/peijiepang/Downloads/pycharm-professional-2020.1.1.dmg");
            client.batchThreadDownload("/Users/peijiepang/Downloads/pycharm-professional-2020.1.1.dmg");
            client.shutdown();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        long end = System.currentTimeMillis();
        LOGGER.warn("下载时间:{}",end-start);
    }

    private ManagedChannel managedChannel;

    private RangeChunkerGrpc.RangeChunkerBlockingStub blockingStub;

    public RangeChunkerClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext(true));
    }

    /**
     * 客户端单线程下载
     * @param name
     * @throws IOException
     */
    public void download(String name) throws IOException{
        RandomAccessFile file = new RandomAccessFile("/Users/peijiepang/Downloads/pycharm11111.dmg","rw");
        FileChannel channel = file.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(64*1024);

        long position = 0;
        RangeRequest rangeRequest = RangeRequest.newBuilder().setFileName(name).setPosition(position).build();
        RangeChunkResponse response = blockingStub.range(rangeRequest);
        while (response.getCode() == 1){
            buf.clear();
            buf.put(response.getChunk().toByteArray());
            buf.flip();
            channel.write(buf);

            rangeRequest = RangeRequest.newBuilder().setFileName(name).setPosition(response.getNextposition()).build();
            response = blockingStub.range(rangeRequest);
        }
        LOGGER.info("下载完成:{}",name);
    }

    /**
     * 客户端单线程下载
     * @param name
     * @throws IOException
     */
    public void batchDownload(String name) throws IOException{
        int chunkSize = 64 * 1024;
        RandomAccessFile file = new RandomAccessFile("/Users/peijiepang/Downloads/ideaIU1111.dmg","rw");
        FileChannel channel = file.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(chunkSize);

        FileSizeRequest fileSizeRequest = FileSizeRequest.newBuilder().setFileName(name).buildPartial();
        FileSizeResponse fileSizeResponse = blockingStub.getFileSize(fileSizeRequest);
        int loopSize = fileSizeResponse.getSize() / chunkSize + 1;
        int start = 0;
        int end = chunkSize;
        for(int i=0;i<loopSize;i++){
            RangeRegionRequest rangeRegionRequest = RangeRegionRequest.newBuilder().setFileName(name).setStart(start).setEnd(end).build();
            Iterator<RangeRegionResponse> rangeRegionResponseIterator = blockingStub.rangeRegion(rangeRegionRequest);
            while (rangeRegionResponseIterator.hasNext()){
                RangeRegionResponse rangeRegionResponse = rangeRegionResponseIterator.next();
                buf.clear();
                buf.put(rangeRegionResponse.getChunk().toByteArray());
                buf.flip();
                channel.write(buf);
            }
            start = end;
            end = start + chunkSize;
        }
        LOGGER.info("下载完成:{}",name);
    }

    /**
     * 客户端多线程下载
     * @param name
     * @throws IOException
     */
    public void batchThreadDownload(String name) throws IOException{
        int chunkSize = 64 * 1024;
        RandomAccessFile file = new RandomAccessFile("/Users/peijiepang/Downloads/pycharm1111.dmg","rw");
        FileChannel channel = file.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(chunkSize);

        List<Future<List<ByteString>>> futures = new ArrayList<>();
        FileSizeRequest fileSizeRequest = FileSizeRequest.newBuilder().setFileName(name).buildPartial();
        FileSizeResponse fileSizeResponse = blockingStub.getFileSize(fileSizeRequest);
        int loopSize = fileSizeResponse.getSize() / chunkSize + 1;
        int start = 0;
        int end = chunkSize;
        for(int i=0;i<loopSize;i++){
            Future<List<ByteString>> future = executorService.submit(new DownloadThread(name,start,end));
            futures.add(future);
            start = end;
            end = start + chunkSize;
        }
        for(int i=0;i<futures.size();i++){
            Future<List<ByteString>> future = futures.get(i);
            try {
                List<ByteString> list = future.get();
                for(int j=0;j<list.size();j++){
                    ByteString byteString = list.get(j);
                    buf.clear();
                    buf.put(byteString.toByteArray());
                    buf.flip();
                    channel.write(buf);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        LOGGER.info("下载完成:{}",name);
    }

    class DownloadThread implements Callable<List<ByteString>> {

        public DownloadThread(String name, int start, int end) {
            this.name = name;
            this.start = start;
            this.end = end;
        }

        private String name;
        private int start;
        private int end;

        @Override
        public List<ByteString> call() throws Exception {
            List<ByteString> result = new ArrayList<>();
            RangeRegionRequest rangeRegionRequest = RangeRegionRequest.newBuilder().setFileName(name).setStart(start).setEnd(end).build();
            Iterator<RangeRegionResponse> rangeRegionResponseIterator = blockingStub.rangeRegion(rangeRegionRequest);
            while (rangeRegionResponseIterator.hasNext()){
                RangeRegionResponse rangeRegionResponse = rangeRegionResponseIterator.next();
                result.add(rangeRegionResponse.getChunk());
            }
            return result;
        }
    }

    /**
     * 关闭客户端
     */
    public void shutdown() throws InterruptedException {
        managedChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    RangeChunkerClient(ManagedChannelBuilder<?> channelBuilder) {
        managedChannel = channelBuilder.build();
        blockingStub = RangeChunkerGrpc.newBlockingStub(managedChannel);
    }

}
