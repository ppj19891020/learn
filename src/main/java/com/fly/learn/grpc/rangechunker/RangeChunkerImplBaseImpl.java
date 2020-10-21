package com.fly.learn.grpc.rangechunker;

import com.demo.grpc.rangechunker.FileSizeRequest;
import com.demo.grpc.rangechunker.FileSizeResponse;
import com.demo.grpc.rangechunker.RangeChunkResponse;
import com.demo.grpc.rangechunker.RangeChunkerGrpc;
import com.demo.grpc.rangechunker.RangeRegionRequest;
import com.demo.grpc.rangechunker.RangeRegionResponse;
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
                rangeChunkResponse = RangeChunkResponse.newBuilder().setNextposition(fileChannel.position()).
                    setCode(1).setChunk(ByteString.copyFrom(array)).build();
            }else{
                // 文件读取完成
                rangeChunkResponse = RangeChunkResponse.newBuilder().setNextposition(fileChannel.position()).
                    setCode(0).build();
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

    @Override
    public void getFileSize(FileSizeRequest request,
        StreamObserver<FileSizeResponse> responseObserver) {
        FileInputStream fileInputStream = null;
        int fileSize = 0;
        try{
            fileInputStream = new FileInputStream(request.getFileName());
            fileSize = fileInputStream.available();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        FileSizeResponse fileSizeResponse = FileSizeResponse.newBuilder().setSize(fileSize).build();
        responseObserver.onNext(fileSizeResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void rangeRegion(RangeRegionRequest request,
        StreamObserver<RangeRegionResponse> responseObserver) {
        int chunkSize = 64 * 1024;
        ByteBuffer byteBuffer = ByteBuffer.allocate(chunkSize);
        RangeRegionResponse rangeRegionResponse = null;
        FileInputStream fileInputStream = null;
        FileChannel fileChannel = null;
        try{
            fileInputStream = new FileInputStream(request.getFileName());
            fileChannel = fileInputStream.getChannel();
            fileChannel.position(request.getStart());
            long length = request.getEnd() - request.getStart();
            int loopSize = (int) length/chunkSize;
            for(int i=0;i<loopSize;i++){
                int readCount = fileChannel.read(byteBuffer);
                if(-1 != readCount){
                    byte[]array = new byte[readCount];
                    byteBuffer.flip();
                    byteBuffer.get(array);
                    byteBuffer.clear();
                    rangeRegionResponse = RangeRegionResponse.newBuilder().setChunk(ByteString.copyFrom(array)).build();
                    responseObserver.onNext(rangeRegionResponse);
                }
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }finally {
            try{
                fileChannel.close();
                fileInputStream.close();
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
        responseObserver.onCompleted();
    }
}
