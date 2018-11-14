package com.fly.learn.reentrantlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author: peijiepang
 * @date 2018/11/14
 * @Description:
 */
public class CyclicBarrierTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(CyclicBarrierTest.class);

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cb = new CyclicBarrier(3, new Thread("barrierAction") {
            public void run() {
                LOGGER.info(Thread.currentThread().getName() + " barrier action");
            }
        });
        MyThread t1 = new MyThread("t1", cb);
        MyThread t2 = new MyThread("t2", cb);
        t1.start();
        t2.start();
        LOGGER.info(Thread.currentThread().getName() + " going to await");
        cb.await();
        LOGGER.info(Thread.currentThread().getName() + " continue");
    }

}

class MyThread extends Thread {
    private final static Logger LOGGER = LoggerFactory.getLogger(MyThread.class);

    private CyclicBarrier cb;
    public MyThread(String name, CyclicBarrier cb) {
        super(name);
        this.cb = cb;
    }

    public void run() {
        LOGGER.info(Thread.currentThread().getName() + " going to await");
        try {
            cb.await();
            LOGGER.info(Thread.currentThread().getName() + " continue");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
