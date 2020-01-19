package com.fly.learn.netty;

import io.netty.util.AttributeKey;

/**
 * @author: peijiepang
 * @date 2020-01-19
 * @Description:
 */
public class Constants {

    public static final AttributeKey<Object> SERVER_NAME_KEY = AttributeKey.newInstance("serverName");

    public static final AttributeKey<Object> CLIENT_NAME_KEY = AttributeKey.newInstance("clientName");

}
