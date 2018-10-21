package com.fly.learn.designpattern.methodfactory;

/**
 * @author: peijiepang
 * @date 2018/10/11
 * @Description:
 */
public class MethodFactoryTest {

    public static void main(String[] args) {
        Factory carFactory = new CarFactory();
        carFactory.get().methodSame();
    }

}
