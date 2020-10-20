package com.fly.learn.grpc.filechunker;

import com.demo.grpc.filechunker.ChunkRequest;
import com.demo.grpc.filechunker.ChunkResponse;
import com.demo.grpc.filechunker.ChunkerGrpc;
import com.fly.learn.filereader.ChannelFileReader;
import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import java.io.IOException;

/**
 * @author: peijiepang
 * @date 2020/10/20
 * @Description:
 */
public class ChunkerImplBaseImpl extends ChunkerGrpc.ChunkerImplBase{

    @Override
    public void chunker(ChunkRequest request, StreamObserver<ChunkResponse> responseObserver) {
        // 文件名
        String filePath = request.getFileName();
        int chunkSize = 64 * 1024;
        try {
            ChannelFileReader channelFileReader = new ChannelFileReader(filePath,chunkSize);
            int batchId = 1;//批次id
            while (channelFileReader.read() != -1) {
                byte[] bytes = channelFileReader.getArray();
                ChunkResponse chunkResponse = ChunkResponse.newBuilder().setChunk(ByteString.copyFrom(bytes)).setBatchid(batchId).build();
                responseObserver.onNext(chunkResponse);
                batchId++;
            }
            responseObserver.onCompleted();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
