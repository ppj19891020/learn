package com.fly.learn.netty.attribute;

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

}
