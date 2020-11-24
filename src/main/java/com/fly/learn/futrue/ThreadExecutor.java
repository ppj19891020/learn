package com.fly.learn.futrue;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定时调度任务添加和取消
 * @author: peijiepang
 * @date 2020/11/23
 * @Description:
 */
public class ThreadExecutor {

    private final static Logger LOGGER = LoggerFactory.getLogger(ThreadExecutor.class);

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                LOGGER.info("Hello");
            }
        };
        ScheduledFuture<?> scheduledFuture =
            scheduledExecutorService.scheduleAtFixedRate(r, 1L, 1L, TimeUnit.SECONDS);
        Thread.sleep(5000L);
        scheduledFuture.cancel(false);
    }

}
