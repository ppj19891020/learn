package com.fly.learn.proxy.jdk.test;

import com.fly.learn.proxy.Person;
import com.fly.learn.proxy.PersonImpl;

import java.lang.reflect.Proxy;
import java.math.BigDecimal;

public class PersonInvocationHandlerTest {

    public static void main(String[] args) {
        PersonInvocationHandler personInvocationHandler = new PersonInvocationHandler(new PersonImpl());
        Person person = (Person) Proxy.newProxyInstance(PersonInvocationHandlerTest.class.getClassLoader(),
                PersonImpl.class.getInterfaces(),personInvocationHandler);
        person.borrow(new BigDecimal(100));
    }

}
