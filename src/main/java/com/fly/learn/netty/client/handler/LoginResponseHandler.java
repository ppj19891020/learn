package com.fly.learn.netty.client.handler;

import com.fly.learn.netty.protocol.packet.LoginRequestPacket;
import com.fly.learn.netty.protocol.packet.LoginResponsePacket;
import com.fly.learn.netty.utils.LoginUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: peijiepang
 * @date 2020-01-19
 * @Description:
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoginResponseHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserName("ppj");
        loginRequestPacket.setPassword("123456");
        // 写数据
//        ctx.channel().writeAndFlush(loginRequestPacket);
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg)
        throws Exception {
        if(msg.isSuccess()){
            LoginUtils.markAsLogin(ctx.channel(),msg.getUserId(),"sss");
            LOGGER.info("用户名:{}登录成功,服务端分配用户id:{}",msg.getUserName(),msg.getUserId());
        }else{
            LOGGER.info("客户端登录失败，原因:"+msg.getReason());
        }
    }
}
