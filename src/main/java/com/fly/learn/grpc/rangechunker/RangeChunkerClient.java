package com.fly.learn.grpc.rangechunker;

import com.demo.grpc.rangechunker.RangeChunkResponse;
import com.demo.grpc.rangechunker.RangeChunkerGrpc;
import com.demo.grpc.rangechunker.RangeRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
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

    public static void main(String[] args){
        long start = System.currentTimeMillis();
        try {
            RangeChunkerClient client = new RangeChunkerClient(HOST, PORT);
            client.download("/Users/peijiepang/Downloads/ideaIU-2020.1.1.dmg");
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
     * 客户端下载
     * @param name
     * @throws IOException
     */
    public void download(String name) throws IOException{
        RandomAccessFile file = new RandomAccessFile("/Users/peijiepang/Downloads/ideaIU1111.dmg","rw");
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
