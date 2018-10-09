package com.fly.learn.proxy.cglib.test;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class PersonMethodInterceptor implements MethodInterceptor {

    public Object getInstance(Object obj){
        //实例化一个增强器，也就是cglib中的一个class generator
        Enhancer enhancer = new Enhancer();
        //设置目标类
        enhancer.setSuperclass(obj.getClass());
        // 设置拦截对象
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("borrow before");
        Object object = methodProxy.invokeSuper(o, objects);
        System.out.println("borrow after");
        return object;
    }
}
