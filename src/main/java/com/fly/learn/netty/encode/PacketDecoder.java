package com.fly.learn.netty.encode;

import com.fly.learn.netty.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 解码
 * @author: peijiepang
 * @date 2020-01-19
 * @Description:
 */
public class PacketDecoder extends ByteToMessageDecoder{

    private final static Logger LOGGER = LoggerFactory.getLogger(PacketCodeC.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out)
        throws Exception {
        out.add(PacketCodeC.getInstance().decode(in));
    }
}
