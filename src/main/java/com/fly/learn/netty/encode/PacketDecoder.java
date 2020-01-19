package com.fly.learn.netty.encode;

import com.fly.learn.netty.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

/**
 * 解码
 * @author: peijiepang
 * @date 2020-01-19
 * @Description:
 */
public class PacketDecoder extends ByteToMessageDecoder{

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out)
        throws Exception {
        out.add(PacketCodeC.getInstance().decode(in));
    }
}
