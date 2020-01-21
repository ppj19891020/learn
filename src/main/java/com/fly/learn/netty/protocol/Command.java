package com.fly.learn.netty.protocol;

/**
 * 指令接口
 * @author: peijiepang
 * @date 2020-01-19
 * @Description:
 */
public interface Command {

    /**
     * 登录指令
     */
    Byte LOGIN_REQUEST_COMMAND = 1;

    /**
     * 登录响应指令
     */
    Byte LOGIN_REPONST_COMMAND = 2;

    /**
     * 发送消息
     */
    Byte MESSAGE_REQUEST_COMMAND = 3;

    /**
     * 响应消息
     */
    Byte MESSAGE_RESPONST_COMMAND = 4;

    /**
     * 心跳监测
     */
    Byte HEART_BEAT_REQUEST_PACKET = 5;

    /**
     * 心跳监测
     */
    Byte HEART_BEAT_RESPONSE_PACKET = 6;
}
