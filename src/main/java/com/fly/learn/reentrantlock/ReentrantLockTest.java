package com.fly.learn.reentrantlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: peijiepang
 * @date 2018/11/7
 * @Description:
 */
public class ReentrantLockTest extends Thread{

    private final static Logger LOGGER = LoggerFactory.getLogger(ReentrantLockTest.class);

    private ReentrantLock reentrantLock = new ReentrantLock();

    private CountDownLatch countDownLatch = null;

    public static int j = 0;

    public ReentrantLockTest(String threadName,CountDownLatch countDownLatch) {
        super(threadName);
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        for(int i=0;i<1000;i++){
            //reentrantLock.tryLock(1000,TimeUnit.MILLISECONDS);

            //reentrantLock.lockInterruptibly();

            //ReentrantLock fairLock = new ReentrantLock(true);

            reentrantLock.lock();
            try{
                LOGGER.info("{}:{}",Thread.currentThread().getName(),i);
                j++;
            }finally {
                reentrantLock.unlock();
            }
        }
        countDownLatch.countDown();
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        ReentrantLockTest reentrantLockTest1 = new ReentrantLockTest("thread1",countDownLatch);
        ReentrantLockTest reentrantLockTest2 = new ReentrantLockTest("thread2",countDownLatch);
        reentrantLockTest1.start();
        reentrantLockTest2.start();
        countDownLatch.await();
        LOGGER.info("---------j:{}",j);
    }
}
