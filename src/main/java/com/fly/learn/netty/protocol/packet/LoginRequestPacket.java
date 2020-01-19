package com.fly.learn.netty.protocol.packet;

import com.fly.learn.netty.protocol.Command;

/**
 * 登录请求包
 * @author: peijiepang
 * @date 2020-01-19
 * @Description:
 */
public class LoginRequestPacket extends Packet{

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST_COMMAND;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRequestPacket{" +
            "userName='" + userName + '\'' +
            ", password='" + password + '\'' +
            '}';
    }
}
