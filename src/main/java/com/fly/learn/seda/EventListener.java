package com.fly.learn.seda;

import java.util.concurrent.BlockingQueue;

/**
 * 事件监听器
 * @author: peijiepang
 * @date 2020/7/24
 * @Description:
 */
public class EventListener {

    /**
     * 事件引用队列
     */
    private BlockingQueue<Event> eventQueue;

    /**
     * 事件处理器
     */
    private EventHandler eventHandler;

    /**
     * 事件执行器
     */
    private EventExecutor eventExecutor;

    public EventListener(BlockingQueue<Event> eventQueue, EventHandler eventHandler) {
        this.eventQueue = eventQueue;
        this.eventHandler = eventHandler;
    }

    /**
     * 开启
     */
    public void start(){

    }

    /**
     * 停止
     */
    public void stop(){

    }

}
