package com.fly.learn.netty.protocol.packet;

import com.fly.learn.netty.protocol.Command;

/**
 * @author: peijiepang
 * @date 2020-01-21
 * @Description:
 */
public class HeartBeatResponsePacket extends Packet{

    @Override
    public Byte getCommand() {
        return Command.HEART_BEAT_RESPONSE_PACKET;
    }
}
