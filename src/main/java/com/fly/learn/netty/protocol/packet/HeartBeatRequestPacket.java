package com.fly.learn.netty.protocol.packet;

import static com.fly.learn.netty.protocol.Command.HEART_BEAT_REQUEST_PACKET;

/**
 * @author: peijiepang
 * @date 2020-01-21
 * @Description:
 */
public class HeartBeatRequestPacket extends Packet{

    @Override
    public Byte getCommand() {
        return HEART_BEAT_REQUEST_PACKET;
    }
}
