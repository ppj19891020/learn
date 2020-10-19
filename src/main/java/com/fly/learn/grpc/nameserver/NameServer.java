package com.fly.learn.grpc.nameserver;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: peijiepang
 * @date 2020/10/16
 * @Description:
 */
public class NameServer {
    private Logger logger = LoggerFactory.getLogger(NameServer.class.getName());

    private static final int DEFAULT_PORT = 8088;

    private int port;//服务端口号

    private Server server;

    public NameServer(int port) {
        this(port, ServerBuilder.forPort(port));
    }

    public NameServer(int port, ServerBuilder<?> serverBuilder){
        this.port = port;
        server = serverBuilder.addService(new NameServiceImplBaseImpl()).build();
    }

    private void start() throws IOException {
        server.start();
        logger.info("Server has started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                NameServer.this.stop();
            }
        });
    }

    private void stop() {
        if(server != null)
            server.shutdown();

    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        NameServer nameServer;
        if(args.length > 0){
            nameServer = new NameServer(Integer.parseInt(args[0]));
        }else{
            nameServer = new NameServer(DEFAULT_PORT);
        }
        nameServer.start();
        nameServer.blockUntilShutdown();
    }

}
