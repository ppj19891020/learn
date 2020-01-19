package com.fly.learn.netty.protocol.packet;

import com.fly.learn.netty.protocol.Command;

/**
 * 发送消息request
 * @author: peijiepang
 * @date 2020-01-19
 * @Description:
 */
public class MessageRequestPacket extends Packet{

    /**
     * 消息
     */
    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST_COMMAND;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageRequestPacket{" +
            "message='" + message + '\'' +
            '}';
    }
}
