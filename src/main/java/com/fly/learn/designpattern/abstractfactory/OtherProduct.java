package com.fly.learn.designpattern.abstractfactory;

import com.fly.learn.designpattern.methodfactory.Product;

/**
 * @author: peijiepang
 * @date 2018/10/21
 * @Description:
 */
public class OtherProduct extends Product {
    @Override
    public void productMethod() {
        System.out.println("生产其他商品");
    }
}
