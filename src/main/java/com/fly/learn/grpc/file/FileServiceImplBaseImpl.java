package com.fly.learn.grpc.file;

import com.demo.grpc.file.FileServiceGrpc;
import com.demo.grpc.file.Request;
import com.demo.grpc.file.Response;
import io.grpc.stub.StreamObserver;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author: peijiepang
 * @date 2020/10/19
 * @Description:
 */
public class FileServiceImplBaseImpl extends FileServiceGrpc.FileServiceImplBase{

    @Override
    public void upload(Request request, StreamObserver<Response> responseObserver) {
        byte[] bytes = request.getFile().toByteArray();
        System.out.println(String.format("收到文件%s长度%s", request.getName(), bytes.length));
        File f = new File("/tmp/" + request.getName());
        Response response;
        if (f.exists()) {
            f.delete();
        }
        try (OutputStream os = new FileOutputStream(f)) {
            os.write(bytes);
            response = Response.newBuilder().setCode(1).setMsg("上传成功").build();
        } catch (IOException e) {
            response = Response.newBuilder().setCode(-1).setMsg(String.format("上传失败:%s", e.getMessage())).build();
            e.printStackTrace();
        }
        // 返回数据，完成此次请求
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
