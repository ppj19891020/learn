package com.fly.learn.grpc.filechunker;

import com.demo.grpc.filechunker.ChunkRequest;
import com.demo.grpc.filechunker.ChunkResponse;
import com.demo.grpc.filechunker.ChunkerGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: peijiepang
 * @date 2020/10/19
 * @Description:
 */
public class FileChunkerClient {
    private final static Logger LOGGER = LoggerFactory.getLogger(FileChunkerClient.class);
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8888;

    public static void main(String[] args){
        long start = System.currentTimeMillis();
        try {
            FileChunkerClient client = new FileChunkerClient(HOST, PORT);
            client.download("/Users/peijiepang/Downloads/ideaIU-2020.1.1.dmg");
            client.shutdown();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        long end = System.currentTimeMillis();
        LOGGER.warn("下载时间:{}",end-start);
    }

    private ManagedChannel managedChannel;

    private ChunkerGrpc.ChunkerBlockingStub blockingStub;

    public FileChunkerClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext(true));
    }

    /**
     * 客户端下载
     * @param name
     * @throws IOException
     */
    public void download(String name) throws IOException{
        ChunkRequest request = ChunkRequest.newBuilder().setFileName(name).build();
        Iterator<ChunkResponse> responseIterator = blockingStub.chunker(request);
        RandomAccessFile file = new RandomAccessFile("/Users/peijiepang/Downloads/ideaIU1111.dmg","rw");
        FileChannel channel = file.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(64*1024);

        while (responseIterator.hasNext()){
            ChunkResponse chunkResponse = responseIterator.next();
            System.out.println(Thread.currentThread().getName()+"-"+chunkResponse.getBatchid());
            buf.clear();
            buf.put(chunkResponse.getChunk().toByteArray());
            buf.flip();
            channel.write(buf);
        }
    }

    /**
     * 关闭客户端
     */
    public void shutdown() throws InterruptedException {
        managedChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    FileChunkerClient(ManagedChannelBuilder<?> channelBuilder) {
        managedChannel = channelBuilder.build();
        blockingStub = ChunkerGrpc.newBlockingStub(managedChannel);
    }

}
