package com.fly.learn.netty.client.handler;

import com.fly.learn.netty.protocol.packet.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: peijiepang
 * @date 2020-01-19
 * @Description:
 */
public class MessageResponstHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    private final static Logger LOGGER = LoggerFactory.getLogger(MessageResponstHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket messageResponsePacket)
        throws Exception {
        String fromUserId = messageResponsePacket.getFromUserId();
        System.out.println(fromUserId + ": -> " + messageResponsePacket .getMessage());
        System.out.println("输入消息发送至服务端：");
    }
}
