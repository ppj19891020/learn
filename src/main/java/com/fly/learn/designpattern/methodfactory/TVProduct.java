package com.fly.learn.designpattern.methodfactory;

/**
 * @author: peijiepang
 * @date 2018/10/10
 * @Description: tv
 */
public class TVProduct extends Product {
    @Override
    public void productMethod() {
        System.out.println("生产电视");
    }
}
