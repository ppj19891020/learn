package com.fly.learn.proxy.jdk.custom;

import java.lang.reflect.Method;

public class PersonInvocationHandler implements CustomInvocationHandler{

    private Object obj;

    public PersonInvocationHandler(Object object){
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
