package com.fly.learn.netty.server;

import com.fly.learn.netty.Constants;
import com.fly.learn.netty.encode.PacketDecoder;
import com.fly.learn.netty.encode.PacketEncoder;
import com.fly.learn.netty.encode.Spliter;
import com.fly.learn.netty.server.handler.AuthHandler;
import com.fly.learn.netty.handler.IMIdleStateHandler;
import com.fly.learn.netty.server.handler.LoginRequestHandler;
import com.fly.learn.netty.server.handler.MessageRequestHandler;
import com.fly.learn.netty.server.handler.ServerHeartBeatHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * netty server
 * @author: peijiepang
 * @date 2020-01-17
 * @Description:
 */
public class NettyServer {

    private final static Logger LOGGER = LoggerFactory.getLogger(NettyServer.class);

    private static final int BEGIN_PORT = 8000;
    private static final String SERVER_NAME_VALUE = "nettyServer";
    public static final AttributeKey<Object> CLIENT_KEY = AttributeKey.newInstance("clientKey");
    public static final String CLIENT_VALUE = "clientValue";

    /**
     * 01: 服务端启动流程介绍[https://www.jianshu.com/p/ec3ebb396943]
     * 要启动Netty服务端,必须要指定三类属性,分别是线程模型、IO模型、连接读写处理逻辑
     * Netty服务端启动的流程是创建引导类给引导类指定线程模型,IO模型,连接读写处理逻辑,绑定端口之后服务端就启动起来
     * bind方法是异步的通过异步机制来实现端口递增绑定
     * Netty服务端启动额外的参数,主要包括给服务端channel或者channel设置属性值,设置底层TCP参数
     */
    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup,workerGroup)
            .channel(NioServerSocketChannel.class)//指定nio线程模型
            .handler(new ChannelInitializer<ServerSocketChannel>() {
                @Override
                protected void initChannel(ServerSocketChannel serverSocketChannel)
                    throws Exception {
                    LOGGER.info("服务端启动中");
                }
            })
            .attr(Constants.SERVER_NAME_KEY, SERVER_NAME_VALUE)//给服务端的channel增加额外属性
            .childAttr(CLIENT_KEY, CLIENT_VALUE)//给每一条连接指定自定义属性
            .option(ChannelOption.SO_BACKLOG,1024)//表示系统用于临时存放已完成三次握手的请求的队列的最大长度，如果连接建立频繁，服务器处理创建新连接较慢，可以适当调大这个参数
            .childOption(ChannelOption.SO_KEEPALIVE,true)//ChannelOption.SO_KEEPALIVE表示是否开启TCP底层心跳机制，true为开启
            .childOption(ChannelOption.SO_REUSEADDR,true)//表示端口释放后立即就可以被再次使用,因为一般来说,一个端口释放后会等待两分钟之后才能再被使用
            .childOption(ChannelOption.TCP_NODELAY,true)//表示是否开始Nagle算法，true表示关闭，false表示开启，通俗地说，如果要求高实时性，有数据发送时就马上发送，就关闭，如果需要减少发送次数减少网络交互，就开启
            .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
//                    socketChannel.pipeline().addLast(new ServerHandler());
                    socketChannel.pipeline().addLast(new IMIdleStateHandler());
                    //拆包
                    socketChannel.pipeline().addLast(new Spliter());
//                    socketChannel.pipeline().addLast(new LifeCyCleTestHandler());
                    socketChannel.pipeline().addLast(new PacketDecoder());
                    socketChannel.pipeline().addLast(new ServerHeartBeatHandler());
                    socketChannel.pipeline().addLast(new LoginRequestHandler());
                    socketChannel.pipeline().addLast(new AuthHandler());
                    socketChannel.pipeline().addLast(new MessageRequestHandler());
                    socketChannel.pipeline().addLast(new PacketEncoder());
                }
            });
        bind(serverBootstrap,BEGIN_PORT);
    }

    /**
     * server端绑定端口，增减端口递增重试功能
     * @param serverBootstrap
     * @param port
     */
    private static void bind(ServerBootstrap serverBootstrap,int port){
        serverBootstrap.bind(port).addListener(
            new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> future) throws Exception {
                    if(future.isSuccess()){
                        LOGGER.info("端口[{}]绑定成功",port);
                    }else{
                        LOGGER.warn("端口[{}]绑定失败，将重新绑定",port);
                        bind(serverBootstrap,port+1);
                    }
                }
            });
    }

}
