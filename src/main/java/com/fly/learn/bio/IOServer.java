package com.fly.learn.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * bio server
 * server服务端首先创建serverscoket监听端口，然后创建线程不断调用阻塞方法serverscoket.accpet()获取新的连接，
 * 当获取到新的连接给每条连接创建新的线程负责该连接读取数据，然后读取数据是以字节流的方式
 *
 * 传统的IO模型每个连接创建成功都需要一个线程来维护,每个线程包含一个while死循环,那么1w个连接对应1w个线程,继而1w个while死循环带来如下几个问题:
 * 1.线程资源受限:线程是操作系统中非常宝贵的资源,同一时刻有大量的线程处于阻塞状态是非常严重的资源浪费,操作系统耗不起;
 * 2.线程切换效率低下:单机CPU核数固定,线程爆炸之后操作系统频繁进行线程切换,应用性能急剧下降;
 * 3.数据读写是以字节流为单位效率不高:每次都是从操作系统底层一个字节一个字节地读取数据.
 * @author: peijiepang
 * @date 2020-01-10
 * @Description:
 */
public class IOServer {

    private final static Logger LOGGER = LoggerFactory.getLogger(IOServer.class);

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        new Thread(()->{
            try {
                Socket scoket = serverSocket.accept();
                new Thread(()->{
                    try {
                        byte[] data = new byte[1];
                        InputStream inputStream = scoket.getInputStream();
                        while (true){
                            int len;
                            while ((len = inputStream.read(data)) != -1){
                                LOGGER.info("message:{}",new String(data));
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
