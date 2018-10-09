package com.fly.learn.proxy.jdk.custom;

import java.lang.reflect.Method;

/**
 * 自定义反射handler
 */
public interface CustomInvocationHandler {

    Object invoke(Object proxy, Method method, Object[] args)throws Throwable;

}
