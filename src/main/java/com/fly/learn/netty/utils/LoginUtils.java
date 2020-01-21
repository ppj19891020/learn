package com.fly.learn.netty.utils;


import com.fly.learn.netty.attribute.Attributes;
import com.fly.learn.netty.session.Session;
import io.netty.channel.Channel;
import io.netty.util.Attribute;
import java.util.HashMap;
import java.util.Map;

/**
 * 判断是否登录
 * @author: peijiepang
 * @date 2020-01-19
 * @Description:
 */
public class LoginUtils {

    /**
     * userid 绑定channel
     */
    private static Map<String,Channel> userIdChannelMap = new HashMap<>();

    /**
     * 设置登录成功标志
     * @param channel
     */
    public static void  markAsLogin(Channel channel,String userId,String userName){
//        channel.attr(Attributes.LOGIN).set(true);
//        channel.attr(Attributes.USER_NAME).set(userName);
        Session session = new Session();
        session.setUserId(userId);
        session.setUserName(userName);
        channel.attr(Attributes.SESSION).set(session);
        userIdChannelMap.put(userId,channel);
    }

    /**
     * 根据用户id获取channel
     * @param userId
     * @return
     */
    public static Channel getChannelByUserId(String userId){
        return userIdChannelMap.get(userId);
    }

    /**
     * 删除session
     * @param channel
     */
    public static void removeSession(Channel channel){
        Session session = channel.attr(Attributes.SESSION).get();
        if(null != session){
           userIdChannelMap.remove(session.getUserId());
        }
    }

    /**
     * 获取session
     * @param channel
     * @return
     */
    public static Session getSession(Channel channel){
        Session session = channel.attr(Attributes.SESSION).get();
        return session;
    }

    /**
     * 判断是否登录成功
     * @param channel
     * @return
     */
    public static boolean hasLogin(Channel channel){
        Attribute<Session> loginAttr = channel.attr(Attributes.SESSION);
        return loginAttr.get() != null;
    }

}
