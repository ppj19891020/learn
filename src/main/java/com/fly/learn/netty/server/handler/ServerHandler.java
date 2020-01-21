package com.fly.learn.netty.server.handler;

import com.fly.learn.netty.protocol.packet.LoginRequestPacket;
import com.fly.learn.netty.protocol.packet.LoginResponsePacket;
import com.fly.learn.netty.protocol.packet.MessageRequestPacket;
import com.fly.learn.netty.protocol.packet.MessageResponsePacket;
import com.fly.learn.netty.protocol.packet.Packet;
import com.fly.learn.netty.protocol.PacketCodeC;
import com.fly.learn.netty.utils.LoginUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * server处理端处理器
 * @author: peijiepang
 * @date 2020-01-19
 * @Description:
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    private final static Logger LOGGER = LoggerFactory.getLogger(ServerHandler.class);

    /**
     * 服务端读取客户端发来的消息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        //解码
        Packet packet = PacketCodeC.getInstance().decode(byteBuf);
        LOGGER.info("服务端接受客户端的请求，消息内容:{}",packet.toString());

        if(packet instanceof LoginRequestPacket){
            //服务端给客户端响应数据
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
            if(loginRequestPacket.getUserName().equalsIgnoreCase("ppj") &&
                loginRequestPacket.getPassword().equalsIgnoreCase("123456")){
                loginResponsePacket.setSuccess(true);
                LoginUtils.markAsLogin(ctx.channel(),loginRequestPacket.getUserName(),"ssss");
                LOGGER.info("客户端登录成功");
            }else{
                loginResponsePacket.setSuccess(false);
                loginResponsePacket.setReason("账号密码错误");
                LOGGER.info("客户端登录失败");
            }
            ByteBuf responByteBuf = PacketCodeC.getInstance().encode(ctx.alloc().buffer(),loginResponsePacket);
            ctx.channel().writeAndFlush(responByteBuf);
        }else if(packet instanceof MessageRequestPacket){
            MessageRequestPacket messageRequestPacket = (MessageRequestPacket)packet;
            System.out.println(new Date()+" :收到客户端消息:"+messageRequestPacket.getMessage());

            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setMessage("服务端回复["+messageRequestPacket.getMessage()+"]");
            ByteBuf responseByteBuf = PacketCodeC.getInstance().encode(ctx.alloc().buffer(),messageResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        }else{
            //不支持该消息格式
        }


    }
}
