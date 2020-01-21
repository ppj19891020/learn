package com.fly.learn.netty.server.handler;

import com.fly.learn.netty.protocol.packet.LoginRequestPacket;
import com.fly.learn.netty.protocol.packet.LoginResponsePacket;
import com.fly.learn.netty.utils.LoginUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 处理登录请求
 * @author: peijiepang
 * @date 2020-01-19
 * @Description:
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoginRequestPacket.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg)
        throws Exception {
        ctx.channel().writeAndFlush(login(ctx,msg));
    }

    /**
     * 登录处理逻辑
     * @param loginRequestPacket
     * @return
     */
    private LoginResponsePacket login(ChannelHandlerContext ctx,LoginRequestPacket loginRequestPacket){
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
//        if(loginRequestPacket.getUserName().equalsIgnoreCase("ppj") &&
//            loginRequestPacket.getPassword().equalsIgnoreCase("123456")){
//            loginResponsePacket.setSuccess(true);
//            LoginUtils.markAsLogin(ctx.channel(),loginRequestPacket.getUserName());
//            LOGGER.info("客户端登录成功");
//        }else{
//            loginResponsePacket.setSuccess(false);
//            loginResponsePacket.setReason("账号密码错误");
//            LOGGER.info("客户端登录失败");
//        }

        String userId = UUID.randomUUID().toString();
        loginResponsePacket.setUserId(userId);
        loginResponsePacket.setUserName(loginRequestPacket.getUserName());
        loginResponsePacket.setSuccess(true);
        LOGGER.info("客户端[{}]登录成功，用户id:{}",loginRequestPacket.getUserName(),userId);
        LoginUtils.markAsLogin(ctx.channel(),userId,loginRequestPacket.getUserName());
        return loginResponsePacket;
    }
}
