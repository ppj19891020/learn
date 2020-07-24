package com.fly.learn.seda;

/**
 * @author: peijiepang
 * @date 2020/7/24
 * @Description:
 */
public interface Stage {

    /**
     * 推送消息
     * @param event
     */
    void push(Event event);

    /**
     * 启动
     */
    void start();

    /**
     * 停止
     */
    void stop();

}
