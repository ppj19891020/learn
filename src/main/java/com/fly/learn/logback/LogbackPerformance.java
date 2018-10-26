package com.fly.learn.logback;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * 测试logback性能
 * @author: peijiepang
 * @date 2018/10/25
 * @Description:
 */
public class LogbackPerformance {

    Logger logger =  LoggerFactory.getLogger(LogbackPerformance.class);

    /**
     * 50个线程循环100000
     * @throws InterruptedException
     */
    @Test
    public void testThread() throws InterruptedException {
        int THREAD_NUM = 50;
        final int LOOP_NUM = 100000;
        logger.info("ssss");
        final CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUM);
        long start = System.currentTimeMillis();
        for(int x= 0;x < THREAD_NUM;x++){
            new Thread(new Runnable() {
                public void run() {
                    for (int y = 0; y < LOOP_NUM; y++) {
                        logger.info("Info Message!");
                    }
                    countDownLatch.countDown();
                }
            }).start();
        }
        countDownLatch.await();
        logger.info("------{}",System.currentTimeMillis() - start);
        System.out.println(System.currentTimeMillis() - start);
    }

    /**
     * 1个线程循环100*100000
     */
    @Test
    public void test() {
        int X_NUM = 100;
        int Y_NUM = 100000;

        long start = System.currentTimeMillis();
        for(int x=0;x<X_NUM;x++) {
            for (int y = 0; y < Y_NUM; y++) {
                logger.info("Info Message!");
            }
        }
        System.out.print(System.currentTimeMillis()-start);
    }

}
