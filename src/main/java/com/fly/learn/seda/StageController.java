package com.fly.learn.seda;

import java.util.concurrent.BlockingQueue;

/**
 * 控制器
 * @author: peijiepang
 * @date 2020/7/24
 * @Description:
 */
public class StageController {

    private String stageName;

    private Event event;

    private EventHandler eventHandler;

    /**
     * 事件队列
     */
    private BlockingQueue<Event> blockingQueue;

    /**
     * 监听器
     */
    private EventListener eventListener;

    public StageController(Event event, EventHandler eventHandler) {
        this.event = event;
        this.eventHandler = eventHandler;
    }

    public void push(Event event) throws InterruptedException {
        blockingQueue.put(event);
    }

    public void start(){
        eventListener.start();
    }

    public void stop(){
        eventListener.stop();
    }

}
