package com.fly.learn.netty.client;

import com.fly.learn.netty.Constants;
import com.fly.learn.netty.client.handler.ClientHeartBeatResponseHandler;
import com.fly.learn.netty.client.handler.HeartBeatTimeHandler;
import com.fly.learn.netty.client.handler.LoginResponseHandler;
import com.fly.learn.netty.client.handler.MessageResponstHandler;
import com.fly.learn.netty.encode.PacketDecoder;
import com.fly.learn.netty.encode.PacketEncoder;
import com.fly.learn.netty.encode.Spliter;
import com.fly.learn.netty.handler.IMIdleStateHandler;
import com.fly.learn.netty.protocol.packet.LoginRequestPacket;
import com.fly.learn.netty.protocol.packet.MessageRequestPacket;
import com.fly.learn.netty.utils.LoginUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: peijiepang
 * @date 2020-01-17
 * @Description:
 */
public class NettyClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(NettyClient.class);
    private final static int MAX_RETRY = 10;
    private static final String CLIENT_NAME_VALUE = "nettyClient-1";
    public static Bootstrap bootstrap = new Bootstrap();

    /**
     * 自动重连机制
     * @param bootstrap
     * @param host
     * @param port
     * @param retry
     */
    public static void connection(Bootstrap bootstrap,String host,int port,int retry){
        bootstrap.connect(host,port).addListener(future -> {
            if(future.isSuccess()){
                LOGGER.info("连接成功,host:{} port:{}",host,port);
                Channel channel = ((ChannelFuture)future).channel();
                startConsoleThread(channel);
            }else{
                if(retry == 0){
                    LOGGER.error("连接失败，重连次数已用完");
                }else {
                    int order = (MAX_RETRY - retry)+1;
                    int delay = 1 << order;//每次递增时间间隔：1，2，4，8，16....
                    LOGGER.info("开始第{}次重试连接,delay:{}",order,delay);
                    bootstrap.config().group().schedule(()->{
                        connection(bootstrap,host,port,retry-1);
                    },delay, TimeUnit.SECONDS);
                }
            }
        });
    }

    /**
     * 开启控制台消息发送
     * @param channel
     */
    private static void startConsoleThread(Channel channel){
        new Thread(()->{
            while (!Thread.interrupted()){
                if(!LoginUtils.hasLogin(channel)){
                    System.out.println("请输入用户名：");
                    Scanner sc = new Scanner(System.in);
                    String userName = sc.nextLine();
                    LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
                    loginRequestPacket.setUserName(userName);
                    loginRequestPacket.setPassword("123456");
                    channel.writeAndFlush(loginRequestPacket);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    System.out.println("输入消息发送至服务端：");
                    Scanner sc = new Scanner(System.in);
                    String line = sc.nextLine();
                    if(line.split(" ").length != 2){
                        System.out.println("请用空格分隔消息，比如'用户id 发送消息内容'");
                    }else{
                        String userId = line.split(" ")[0];
                        String message = line.split(" ")[1];
                        MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
                        messageRequestPacket.setUserId(userId);
                        messageRequestPacket.setMessage(message);
                        channel.writeAndFlush(messageRequestPacket);
                    }
                }
            }
        }).start();
    }

    public static void main(String[] args){
        NioEventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group)
            .channel(NioSocketChannel.class)
            .attr(Constants.CLIENT_NAME_KEY,CLIENT_NAME_VALUE)
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)//表示连接的超时时间，超过这个时间还是建立不上的话则代表连接失败
            .option(ChannelOption.SO_KEEPALIVE, true)//表示是否开启 TCP 底层心跳机制，true 为开启
            .option(ChannelOption.TCP_NODELAY, true)//表示是否开始 Nagle 算法，true 表示关闭，false 表示开启，通俗地说，如果要求高实时性，有数据发送时就马上发送，就设置为 true 关闭，如果需要减少发送次数减少网络交互，就设置为 false 开启
            .handler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel ch) throws Exception {
//                    ch.pipeline().addLast(new ClientHandler());
//                    ch.pipeline().addLast(new IMIdleStateHandler());
                    //拆包
                    ch.pipeline().addLast(new Spliter());
//                    ch.pipeline().addLast(new LifeCyCleTestHandler());
                    ch.pipeline().addLast(new PacketDecoder());
                    ch.pipeline().addLast(new LoginResponseHandler());
                    ch.pipeline().addLast(new MessageResponstHandler());
                    ch.pipeline().addLast(new PacketEncoder());
                    ch.pipeline().addLast(new HeartBeatTimeHandler());
                }
            });
        //连接server端
        connection(bootstrap,"127.0.0.1",8000,MAX_RETRY);
    }

}
