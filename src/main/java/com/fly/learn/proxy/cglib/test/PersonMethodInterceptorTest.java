package com.fly.learn.proxy.cglib.test;

import com.fly.learn.proxy.PersonImpl;

import java.math.BigDecimal;

public class PersonMethodInterceptorTest {

    public static void main(String[] args) {
        PersonMethodInterceptor personMethodInterceptor = new PersonMethodInterceptor();
        PersonImpl person = (PersonImpl) personMethodInterceptor.getInstance(new PersonImpl());
        person.borrow(new BigDecimal(1000));
    }

}
