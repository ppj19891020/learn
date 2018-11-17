package com.fly.learn.reentrantlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author: peijiepang
 * @date 2018/11/17
 * @Description:
 */
public class SemaphoreTest extends Thread{

    private final static Logger LOGGER = LoggerFactory.getLogger(SemaphoreTest.class);

    private Semaphore semaphore;


    public SemaphoreTest(String threadName,Semaphore semaphore) {
        this.semaphore = semaphore;
        this.setName(threadName);
    }

    @Override
    public void run(){
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("{}......",Thread.currentThread().getName());
        semaphore.release();
    }

    public static void main(String[] args) {
        //公平锁
        Semaphore semaphore = new Semaphore(2,true);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i=0;i<10;i++){
            executorService.submit(new SemaphoreTest("thread_"+1,semaphore));
        }
        executorService.shutdown();
        LOGGER.info("finish.......");
    }
}
