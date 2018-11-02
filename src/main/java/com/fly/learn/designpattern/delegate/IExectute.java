package com.fly.learn.designpattern.delegate;

/**
 * @author: peijiepang
 * @date 2018/10/24
 * @Description: 委托设计模式
 */
public interface IExectute {

    /**
     * 回调
     * @param message
     */
    void callback(String message);
}
