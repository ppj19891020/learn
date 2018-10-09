package com.fly.learn.proxy.jdk.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * jdk 动态代理实现
 */
public class PersonInvocationHandler implements InvocationHandler {

    private Object obj;

    public PersonInvocationHandler(Object object) {
        this.obj = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("borrow before");
        method.invoke(obj,args);
        System.out.println("borrow after");
        return null;
    }
}
