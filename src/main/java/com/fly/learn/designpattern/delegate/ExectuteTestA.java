package com.fly.learn.designpattern.delegate;

/**
 * @author: peijiepang
 * @date 2018/10/24
 * @Description:
 */
public class ExectuteTestA implements IExectute{
    @Override
    public void callback(String message) {
        System.out.println("ExectuteTestA:"+message);
    }
}
