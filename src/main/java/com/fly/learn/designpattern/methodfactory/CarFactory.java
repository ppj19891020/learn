package com.fly.learn.designpattern.methodfactory;

/**
 * @author: peijiepang
 * @date 2018/10/11
 * @Description:
 */
public class CarFactory extends Factory{
    @Override
    public Product get() {
        return new CarProduct();
    }
}
