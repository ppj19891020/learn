package com.fly.learn.proxy.jdk;

import java.math.BigDecimal;

public interface Person {

    /**
     * 借钱
     */
    public void borrow(BigDecimal money);

    public void borrow();
}
