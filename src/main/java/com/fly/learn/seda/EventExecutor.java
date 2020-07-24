package com.fly.learn.seda;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: peijiepang
 * @date 2020/7/24
 * @Description:
 */
public class EventExecutor {

    private ThreadPoolExecutor executor;

    public void submit(EventHandler handler){
        executor.submit(handler);
    }

}
