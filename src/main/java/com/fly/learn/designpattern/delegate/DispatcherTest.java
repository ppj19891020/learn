package com.fly.learn.designpattern.delegate;

/**
 * @author: peijiepang
 * @date 2018/11/5
 * @Description:
 */
public class DispatcherTest {

    public static void main(String[] args) {
        Dispatcher dispatcher = new Dispatcher(new ExectuteTestA());
        dispatcher.callback("aaaaa");
    }

}
