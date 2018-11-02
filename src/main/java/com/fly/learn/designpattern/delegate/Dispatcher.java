package com.fly.learn.designpattern.delegate;

/**
 * @author: peijiepang
 * @date 2018/10/24
 * @Description:
 */
public class Dispatcher implements IExectute{

    private IExectute exectute;

    @Override
    public void callback(String message) {
        this.exectute.callback(message);
    }
}
