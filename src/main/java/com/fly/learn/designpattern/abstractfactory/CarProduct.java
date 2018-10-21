package com.fly.learn.designpattern.abstractfactory;

import com.fly.learn.designpattern.methodfactory.Product;

/**
 * @author: peijiepang
 * @date 2018/10/10
 * @Description:
 */
public class CarProduct extends Product {

    @Override
    public void productMethod() {
        System.out.println("生产汽车");
    }
}
