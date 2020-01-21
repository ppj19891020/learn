package com.fly.learn.netty.server.handler;

import com.fly.learn.netty.protocol.packet.HeartBeatRequestPacket;
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
public class ServerHeartBeatHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {

    private final static Logger LOGGER = LoggerFactory.getLogger(ServerHeartBeatHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestPacket msg)
        throws Exception {
        LOGGER.info("接收到客户端ping");
        ctx.writeAndFlush(new HeartBeatResponsePacket());
    }
}
