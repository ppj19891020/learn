package com.fly.learn.designpattern.abstractfactory;

/**
 * @author: peijiepang
 * @date 2018/10/21
 * @Description:
 */
public class AbstractFactoryTest {

    public static void main(String[] args) {
        Factory factory = new FactoryA();
        factory.showCar();
        factory.showTV();
    }

}
