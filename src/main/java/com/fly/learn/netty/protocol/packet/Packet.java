package com.fly.learn.netty.protocol.packet;

/**
 * 协议包定义
 * |--------魔数（5字节）---------|--------版本号（1字节）---------|--------序列化算法（1字节）---------|--------指令（1字节）---------|--------数据长度（4字节）---------|--------数据---------|
 * @author: peijiepang
 * @date 2020-01-19
 * @Description:
 */
public abstract class Packet {

    /**
     * 版本号
     */
    private Byte version = 1;

    /**
     * 抽象方法
     * @return
     */
    public abstract Byte getCommand();

    public Byte getVersion() {
        return version;
    }

    public void setVersion(Byte version) {
        this.version = version;
    }
}
