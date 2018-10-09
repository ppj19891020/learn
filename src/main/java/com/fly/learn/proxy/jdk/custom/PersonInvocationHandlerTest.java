package com.fly.learn.proxy.jdk.custom;

import com.fly.learn.proxy.Person;
import com.fly.learn.proxy.PersonImpl;

import java.math.BigDecimal;

public class PersonInvocationHandlerTest {

    public static void main(String[] args) {
        PersonInvocationHandler personInvocationHandler = new PersonInvocationHandler(new PersonImpl());
        Person person = (Person) CustomProxy.newProxyInstance(new CustomClassLoader(),
                PersonImpl.class.getInterfaces(),personInvocationHandler);
        person.borrow(new BigDecimal(100));
    }

}
