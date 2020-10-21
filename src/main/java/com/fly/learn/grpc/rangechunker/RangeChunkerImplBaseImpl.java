package com.fly.learn.grpc.rangechunker;

import com.demo.grpc.rangechunker.RangeChunkResponse;
import com.demo.grpc.rangechunker.RangeChunkerGrpc;
import com.demo.grpc.rangechunker.RangeRequest;
import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 * @author: peijiepang
 * @date 2020/10/21
 * @Description:
 */
public class RangeChunkerImplBaseImpl extends RangeChunkerGrpc.RangeChunkerImplBase {

    @Override
    public void range(RangeRequest request, StreamObserver<RangeChunkResponse> responseObserver) {
        // 文件名
        String filePath = request.getFileName();
        int chunkSize = 64 * 1024;

        RangeChunkResponse rangeChunkResponse = null;
        FileInputStream fileInputStream = null;
        FileChannel fileChannel = null;
        try{
            fileInputStream = new FileInputStream(filePath);
            fileChannel = fileInputStream.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(chunkSize);
            // 定位偏移量
            fileChannel.position(request.getPosition());
            int readCount = fileChannel.read(byteBuffer);
            if(readCount != -1){
                byte[]array = new byte[readCount];
                byteBuffer.flip();
                byteBuffer.get(array);
                byteBuffer.clear();
                // 文件读取
                rangeChunkResponse = RangeChunkResponse.newBuilder().setNextposition(fileChannel.position()).setCode(1).setChunk(ByteString.copyFrom(array)).build();
            }else{
                // 文件读取完成
                rangeChunkResponse = RangeChunkResponse.newBuilder().setNextposition(fileChannel.position()).setCode(0).build();
            }
        }catch (IOException ex){
            ex.printStackTrace();
            rangeChunkResponse = RangeChunkResponse.newBuilder().setCode(-1).build();
        }finally {
            try {
                if(null != fileInputStream){
                    fileInputStream.close();
                }
                if(null != fileChannel){
                    fileChannel.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        responseObserver.onNext(rangeChunkResponse);
        responseObserver.onCompleted();
    }
}
