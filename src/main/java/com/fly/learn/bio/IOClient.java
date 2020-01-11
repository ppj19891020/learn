package com.fly.learn.bio;

import java.io.IOException;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * bio client
 * Client客户端连接服务端8000端口每隔2秒向服务端写带有时间戳的 "hello world"
 * @author: peijiepang
 * @date 2020-01-11
 * @Description:
 */
public class IOClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(IOClient.class);

    public static void main(String[] args) {
        new Thread(()->{
            try{
                Socket socket = new Socket("127.0.0.1",8888);
                while (true){
                    String message = "hello world";
                    LOGGER.info("bytes：{} size:{}",message.getBytes(),message.getBytes().length);
                    socket.getOutputStream().write(message.getBytes());
                    socket.getOutputStream().flush();
                    Thread.sleep(2000l);
                }
            }catch (IOException ex){
                ex.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
