package com.fly.learn.designpattern.simplefactory;

/**
 * @author: peijiepang
 * @date 2018/10/10
 * @Description:
 */
public class ProductFactory {

    public static Product createProduct(String type) throws Exception{
        switch (type){
            case "car":
                return new CarProduct();
            case "tv":
                return new TVProduct();
            default:
                throw new Exception("没有商品");
        }
    }

}
