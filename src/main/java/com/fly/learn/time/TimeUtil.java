package com.fly.learn.time;

import java.util.concurrent.TimeUnit;

/**
 * @author: peijiepang
 * @date 2019-12-19
 * @Description:
 */
public class TimeUtil {

    /**
     * 当前时间
     */
    private static volatile long currentTimeMills;

    static {
        currentTimeMills = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                currentTimeMills = System.currentTimeMillis();
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setName("thread-time-update");
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * 获取当前时间
     * @return
     */
    public static long currentTimeMillis(){
        return currentTimeMills;
    }

}
