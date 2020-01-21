package com.fly.learn.netty.server.handler;

import com.fly.learn.netty.protocol.packet.MessageRequestPacket;
import com.fly.learn.netty.protocol.packet.MessageResponsePacket;
import com.fly.learn.netty.utils.LoginUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 请求发送消息
 * @author: peijiepang
 * @date 2020-01-19
 * @Description:
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    private final static Logger LOGGER = LoggerFactory.getLogger(MessageRequestPacket.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket)
        throws Exception {
        LOGGER.info(new Date()+" :收到客户端消息:"+messageRequestPacket.getMessage());
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();

        messageResponsePacket.setFromUserId(messageRequestPacket.getUserId());
        messageResponsePacket.setMessage("服务端回复["+messageRequestPacket.getMessage()+"]");
        //查询userid所对应channel通道，然后发送消息
        Channel channel = LoginUtils.getChannelByUserId(messageRequestPacket.getUserId());
        if(null != channel){
            channel.writeAndFlush(messageResponsePacket);
        }else{
            LOGGER.warn("userid:{} 不在线！！！",messageRequestPacket.getUserId());
            messageResponsePacket.setMessage("该用户不在线，请稍后联系！！");
            ctx.channel().writeAndFlush(messageResponsePacket);
        }
    }
}
