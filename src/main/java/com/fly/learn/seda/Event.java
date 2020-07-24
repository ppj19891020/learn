package com.fly.learn.seda;

/**
 * 事件消息
 * @author: peijiepang
 * @date 2020/7/24
 * @Description:
 */
public class Event {

    /**
     * 事件类型
     */
    private EventType eventType;

    /**
     * 获取事件类型
     * @return
     */
    public EventType getEventType() {
        return eventType;
    }
}
