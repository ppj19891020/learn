package com.fly.learn.netty.utils;


import com.fly.learn.netty.attribute.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * 判断是否登录
 * @author: peijiepang
 * @date 2020-01-19
 * @Description:
 */
public class LoginUtils {

    /**
     * 设置登录成功标志
     * @param channel
     */
    public static void  markAsLogin(Channel channel,String userName){
        channel.attr(Attributes.LOGIN).set(true);
        channel.attr(Attributes.USER_NAME).set(userName);
    }

    /**
     * 判断是否登录成功
     * @param channel
     * @return
     */
    public static boolean hasLogin(Channel channel){
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
        return loginAttr.get() != null;
    }

}
