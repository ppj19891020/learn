package com.fly.learn.time;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 获取当前时间测试,用20线程的测试结果，差10倍左右，解决方案通过一个线程去获取时间，避免多线程并发产生的性能问题
 * ```
 * 测试案例1耗时:3
 * 测试案例2耗时:11
 * ```
 * @author: peijiepang
 * @date 2019-12-19
 * @Description:
 */
public class TimeTest {

    public static void main(String[] args) throws InterruptedException {
        int count = 1000;
        long start = System.currentTimeMillis();
        for(int i=0;i<count;i++){
            System.currentTimeMillis();
        }
        long end = System.currentTimeMillis();
        System.out.println("测试案例1耗时:"+(end - start));

        CountDownLatch countDownLatch = new CountDownLatch(count);
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        start = System.currentTimeMillis();
        for(int i=0;i<count;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.currentTimeMillis();
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        end = System.currentTimeMillis();
        System.out.println("测试案例2耗时:"+(end-start));

        CountDownLatch countDownLatch1 = new CountDownLatch(count);
        start = System.currentTimeMillis();
        for(int i=0;i<count;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    TimeUtil.currentTimeMillis();
                    countDownLatch1.countDown();
                }
            });
        }
        countDownLatch1.await();
        end = System.currentTimeMillis();
        System.out.println("测试案例3耗时:"+(end - start));

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
    }

}
