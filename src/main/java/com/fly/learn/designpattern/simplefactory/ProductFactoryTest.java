package com.fly.learn.designpattern.simplefactory;

/**
 * @author: peijiepang
 * @date 2018/10/10
 * @Description:
 */
public class ProductFactoryTest {

    public static void main(String[] args) {
        try {
            ProductFactory.createProduct("tv").methodSame();
            ProductFactory.createProduct("car").methodSame();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
