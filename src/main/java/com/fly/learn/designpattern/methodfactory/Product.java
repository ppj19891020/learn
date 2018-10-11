package com.fly.learn.designpattern.methodfactory;

/**
 * @author: peijiepang
 * @date 2018/10/10
 * @Description: 产品
 */
public abstract class Product {

    /**
     * 公共方法的实现
     * @Date 2018/10/10 22:58
     * @author peijiepang
     * @return void
     **/
    public void methodSame() {
        System.out.println("开始生产产品");
        this.productMethod();
        System.out.println("生产产品结束");
    }

    /**
     * 声明抽象业务方法
     * @Date 2018/10/10 22:58
     * @author peijiepang
     * @return void
     **/
    public abstract void productMethod();


}
