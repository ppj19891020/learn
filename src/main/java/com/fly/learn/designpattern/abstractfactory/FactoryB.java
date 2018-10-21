package com.fly.learn.designpattern.abstractfactory;

/**
 * @author: peijiepang
 * @date 2018/10/21
 * @Description:
 */
public class FactoryB implements Factory{
    @Override
    public CarProduct showCar() {
        return new CarProduct();
    }

    @Override
    public TVProduct showTV() {
        return new TVProduct();
    }
}
