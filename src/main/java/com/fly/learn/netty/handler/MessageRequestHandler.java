package com.fly.learn.netty.handler;

import com.fly.learn.netty.protocol.packet.MessageRequestPacket;
import com.fly.learn.netty.protocol.packet.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.Date;

/**
 * 请求发送消息
 * @author: peijiepang
 * @date 2020-01-19
 * @Description:
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket)
        throws Exception {
        System.out.println(new Date()+" :收到客户端消息:"+messageRequestPacket.getMessage());
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage("服务端回复["+messageRequestPacket.getMessage()+"]");
        ctx.channel().writeAndFlush(messageResponsePacket);
    }
}
