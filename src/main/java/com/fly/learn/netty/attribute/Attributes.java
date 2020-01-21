package com.fly.learn.netty.attribute;

import com.fly.learn.netty.session.Session;
import io.netty.util.AttributeKey;

/**
 * channel属性
 * @author: peijiepang
 * @date 2020-01-19
 * @Description:
 */
public interface Attributes {

    /**
     * 判读是否登录成功
     */
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

    /**
     * 登录成功用户名
     */
    AttributeKey<String> USER_NAME = AttributeKey.newInstance("userName");

    /**
     * session信息
     */
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
