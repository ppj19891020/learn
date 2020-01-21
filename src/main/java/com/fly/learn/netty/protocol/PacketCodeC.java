package com.fly.learn.netty.protocol;

import static com.fly.learn.netty.protocol.Command.HEART_BEAT_REQUEST_PACKET;
import static com.fly.learn.netty.protocol.Command.HEART_BEAT_RESPONSE_PACKET;
import static com.fly.learn.netty.protocol.Command.LOGIN_REPONST_COMMAND;
import static com.fly.learn.netty.protocol.Command.LOGIN_REQUEST_COMMAND;
import static com.fly.learn.netty.protocol.Command.MESSAGE_REQUEST_COMMAND;
import static com.fly.learn.netty.protocol.Command.MESSAGE_RESPONST_COMMAND;

import com.fly.learn.netty.protocol.packet.HeartBeatRequestPacket;
import com.fly.learn.netty.protocol.packet.HeartBeatResponsePacket;
import com.fly.learn.netty.protocol.packet.LoginRequestPacket;
import com.fly.learn.netty.protocol.packet.LoginResponsePacket;
import com.fly.learn.netty.protocol.packet.MessageRequestPacket;
import com.fly.learn.netty.protocol.packet.MessageResponsePacket;
import com.fly.learn.netty.protocol.packet.Packet;
import com.fly.learn.netty.serialize.Serializer;
import com.fly.learn.netty.serialize.impl.JSONSerializer;
import io.netty.buffer.ByteBuf;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 编解码器
 * @author: peijiepang
 * @date 2020-01-19
 * @Description:
 */
public class PacketCodeC {
    private final static Logger LOGGER = LoggerFactory.getLogger(PacketCodeC.class);

    public static class PacketCodeHold {
        private static final PacketCodeC INSTANCE = new PacketCodeC();
    }

    /**
     * 获取单例模式
     * @return
     */
    public static PacketCodeC getInstance(){
        return PacketCodeHold.INSTANCE;
    }

    /**
     * 魔数
     */
    public static final int MAGIC_NUMBER = 0x12345678;

    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private static final Map<Byte, Serializer> serializerMap;

    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST_COMMAND, LoginRequestPacket.class);
        packetTypeMap.put(LOGIN_REPONST_COMMAND, LoginResponsePacket.class);
        packetTypeMap.put(MESSAGE_REQUEST_COMMAND, MessageRequestPacket.class);
        packetTypeMap.put(MESSAGE_RESPONST_COMMAND, MessageResponsePacket.class);
        packetTypeMap.put(HEART_BEAT_REQUEST_PACKET, HeartBeatRequestPacket.class);
        packetTypeMap.put(HEART_BEAT_RESPONSE_PACKET, HeartBeatResponsePacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }


    /**
     * 编码
     * 首先，我们需要创建一个 ByteBuf，这里我们调用 Netty 的 ByteBuf 分配器来创建，ioBuffer() 方法会返回适配 io 读写相关的内存，它会尽可能创建一个直接内存，直接内存可以理解为不受 jvm 堆管理的内存空间，写到 IO 缓冲区的效果更高。
     * 接下来，我们将 Java 对象序列化成二进制数据包。
     * 最后，我们对照本小节开头协议的设计以及上一小节 ByteBuf 的 API，逐个往 ByteBuf 写入字段，即实现了编码过程，到此，编码过程结束。
     * @param packet
     * @return
     */
    public ByteBuf encode(ByteBuf byteBuf,Packet packet){
        // 序列化java对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        // 实际编码
        byteBuf.writeInt(MAGIC_NUMBER);//魔数
        byteBuf.writeByte(packet.getVersion());//版本号
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());//序列化算法
        byteBuf.writeByte(packet.getCommand());//命令
        byteBuf.writeInt(bytes.length);//数据包长度
        byteBuf.writeBytes(bytes);//数据包
//        LOGGER.info("编码-长度:{}",bytes.length);
        return byteBuf;
    }

    /**
     * 解码
     * 我们假定 decode 方法传递进来的 ByteBuf 已经是合法的（在后面小节我们再来实现校验），即首四个字节是我们前面定义的魔数 0x12345678，这里我们调用 skipBytes 跳过这四个字节。
     * 这里，我们暂时不关注协议版本，通常我们在没有遇到协议升级的时候，这个字段暂时不处理，因为，你会发现，绝大多数情况下，这个字段几乎用不着，但我们仍然需要暂时留着。
     * 接下来，我们调用 ByteBuf 的 API 分别拿到序列化算法标识、指令、数据包的长度。
     * 最后，我们根据拿到的数据包的长度取出数据，通过指令拿到该数据包对应的 Java 对象的类型，根据序列化算法标识拿到序列化对象，将字节数组转换为 Java 对象，至此，解码过程结束。
     * @param byteBuf
     * @return
     */
    public Packet decode(ByteBuf byteBuf){
        byteBuf.skipBytes(4);//跳过魔数
        byteBuf.skipBytes(1);//跳过版本
        byte serializerAlgorithm = byteBuf.readByte();//序列化方式
        byte command = byteBuf.readByte();//指令
        int length = byteBuf.readInt();//数据包长度
//        LOGGER.info("解码-长度:{}",length);
//        System.out.println("剩余内存:"+Runtime.getRuntime().freeMemory()/1024/1024 + " length:"+length);
        byte[] data = new byte[length];//数据包
        byteBuf.readBytes(data);

        Class<? extends Packet> requesType = getRequestType(command);//获取请求参数类型
        Serializer serializer = getSerializer(serializerAlgorithm);//序列化方式
        if(null != requesType && null != serializer){
            return serializer.deserialize(requesType,data);
        }
        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {
        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {
        return packetTypeMap.get(command);
    }

}
