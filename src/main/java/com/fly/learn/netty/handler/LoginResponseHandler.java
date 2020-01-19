package com.fly.learn.netty.handler;

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
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg)
        throws Exception {
        if(msg.isSuccess()){
            LoginUtils.markAsLogin(ctx.channel(),"ppj");
            System.out.println("客户端登录成功");
        }else{
            System.out.println("客户端登录失败，原因:"+msg.getReason());
        }
    }
}
