package com.fly.learn.netty.client.handler;

import com.fly.learn.netty.protocol.packet.LoginRequestPacket;
import com.fly.learn.netty.protocol.packet.LoginResponsePacket;
import com.fly.learn.netty.protocol.packet.MessageResponsePacket;
import com.fly.learn.netty.protocol.packet.Packet;
import com.fly.learn.netty.protocol.PacketCodeC;
import com.fly.learn.netty.utils.LoginUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: peijiepang
 * @date 2020-01-19
 * @Description:
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    private final static Logger LOGGER = LoggerFactory.getLogger(ClientHandler.class);

    /**
     * 客户端在注册成功之后会调用该方法，响应服务端消息
     * 写数据的逻辑分为两步：首先我们需要获取一个 netty 对二进制数据的抽象 ByteBuf，上面代码中, ctx.alloc() 获取到一个 ByteBuf 的内存管理器，
     * 这个 内存管理器的作用就是分配一个 ByteBuf，然后我们把字符串的二进制数据填充到 ByteBuf，这样我们就获取到了 Netty 需要的一个数据格式，
     * 最后我们调用 ctx.channel().writeAndFlush() 把数据写到服务端
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        ByteBuf byteBuf = ctx.alloc().buffer();
//        byte[] bytes = "客户端连接成功".getBytes();
//        byteBuf.writeBytes(bytes);

        //构建登陆信息
//        LOGGER.info("客户端开始登陆");
//        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
//        loginRequestPacket.setUserName("ppj");
//        loginRequestPacket.setPassword("123456");
//        //编码
//        ByteBuf byteBuf = PacketCodeC.getInstance().encode(ctx.alloc().buffer(),loginRequestPacket);
//        ctx.channel().writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf)msg;
        Packet packet = PacketCodeC.getInstance().decode(byteBuf);
        LOGGER.info("客户端接受到服务端的响应,消息内容为:{}",packet.toString());

        if(packet instanceof LoginResponsePacket){
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket)packet;
            if(loginResponsePacket.isSuccess()){
                LoginUtils.markAsLogin(ctx.channel(),"ppj","");
            }
        }else if(packet instanceof MessageResponsePacket){
            MessageResponsePacket messageResponsePacket = (MessageResponsePacket)packet;
            System.out.println(new Date()+":收到服务端的消息:"+messageResponsePacket.getMessage());
        }else{
            //不支持消息格式
        }
    }
}
