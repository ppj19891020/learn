package com.fly.learn.netty.client.handler;

import com.fly.learn.netty.protocol.packet.HeartBeatResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: peijiepang
 * @date 2020-01-21
 * @Description:
 */
public class ClientHeartBeatResponseHandler extends SimpleChannelInboundHandler<HeartBeatResponsePacket> {

    private final static Logger LOGGER = LoggerFactory.getLogger(ClientHeartBeatResponseHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatResponsePacket msg)
        throws Exception {
        LOGGER.info("接收到服务端的心跳返回包");
    }
}
