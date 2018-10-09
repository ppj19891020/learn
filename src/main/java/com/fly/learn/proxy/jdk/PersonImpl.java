package com.fly.learn.proxy.jdk;

import java.math.BigDecimal;

public class PersonImpl implements Person{

    public void borrow(BigDecimal money) {
        System.out.println("借钱金额:"+money);
    }

    @Override
    public void borrow() {
        System.out.println("借钱金额:---");

    }

}
