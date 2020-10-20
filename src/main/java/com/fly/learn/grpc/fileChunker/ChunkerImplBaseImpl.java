package com.fly.learn.grpc.fileChunker;

import com.demo.grpc.filechunker.ChunkRequest;
import com.demo.grpc.filechunker.ChunkResponse;
import com.demo.grpc.filechunker.ChunkerGrpc;
import io.grpc.stub.StreamObserver;

/**
 * @author: peijiepang
 * @date 2020/10/20
 * @Description:
 */
public class ChunkerImplBaseImpl extends ChunkerGrpc.ChunkerImplBase{

    @Override
    public void chunker(ChunkRequest request, StreamObserver<ChunkResponse> responseObserver) {
        // 文件名
        String fileName = request.getFileName();

    }
}
