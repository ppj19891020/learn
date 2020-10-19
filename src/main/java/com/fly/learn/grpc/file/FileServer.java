package com.fly.learn.grpc.file;

import io.grpc.ServerBuilder;
import java.io.IOException;

/**
 * @author: peijiepang
 * @date 2020/10/19
 * @Description:
 */
public class FileServer {

    private io.grpc.Server server;
    private static final int PORT = 8888;


    public static void main(String[] args) throws IOException, InterruptedException {
        FileServer server = new FileServer();
        server.start(PORT);
        server.await();
    }

    private void start(int port) throws IOException {
        server = ServerBuilder.forPort(port)
            .addService(new FileServiceImplBaseImpl())
            .build()
            .start();
        // 添加钩子,在程序关闭时自动关闭服务端
        addHook();
    }

    private void addHook() {
        Runtime.getRuntime()
            .addShutdownHook(
                new Thread(
                    () -> {
                        System.out.println("监听到JVM停止,正在关闭GRPC服务....");
                        this.stop();
                        System.out.println("服务已经停止...");
                    }));
    }

    /**
     * 关闭服务
     */
    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    public void await() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

}
