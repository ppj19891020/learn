package com.fly.learn.seda;

/**
 * @author: peijiepang
 * @date 2020/7/24
 * @Description:
 */
public interface EventHandler extends Runnable{

    /**
     * 事件处理方法
     * @param event
     */
    void handle(Event event);

}
