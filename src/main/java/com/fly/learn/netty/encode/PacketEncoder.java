package com.fly.learn.netty.encode;

import com.fly.learn.netty.protocol.PacketCodeC;
import com.fly.learn.netty.protocol.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author: peijiepang
 * @date 2020-01-19
 * @Description:
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {


    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        PacketCodeC.getInstance().encode(out,msg);
    }
}
