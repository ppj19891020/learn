package com.fly.learn.netty.protocol.packet;

import com.fly.learn.netty.protocol.Command;

/**
 * @author: peijiepang
 * @date 2020-01-19
 * @Description:
 */
public class LoginResponsePacket extends Packet {

    private boolean success;

    /**
     * 请求失败原因
     */
    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REPONST_COMMAND;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "LoginResponsePacket{" +
            "success=" + success +
            ", reason='" + reason + '\'' +
            '}';
    }
}
