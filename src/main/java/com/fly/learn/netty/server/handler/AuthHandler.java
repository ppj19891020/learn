package com.fly.learn.netty.server.handler;

import com.fly.learn.netty.utils.LoginUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author: peijiepang
 * @date 2020-01-21
 * @Description:
 */
public class AuthHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(!LoginUtils.hasLogin(ctx.channel())){
            //如果没有登录成功就直接关闭
            ctx.channel().close();
        }else{
            //如果登录成功则直接移除
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if (LoginUtils.hasLogin(ctx.channel())) {
            System.out.println("当前连接登录验证完毕，无需再次验证, AuthHandler 被移除");
        } else {
            System.out.println("无登录验证，强制关闭连接,并删除session!");
            LoginUtils.removeSession(ctx.channel());
        }
    }
}
