package com.fly.learn.event;

/**
 * 事件管理器
 */
public interface EventBus {
    void register(Object subscriber);

    void unregister(Object subscriber);

    void post(Event event);
}
