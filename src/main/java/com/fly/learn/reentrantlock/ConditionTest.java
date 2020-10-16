package com.fly.learn.reentrantlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: peijiepang
 * @date 2018/11/8
 * @Description:
 */
public class ConditionTest extends Thread{

    private final static Logger LOGGER = LoggerFactory.getLogger(ConditionTest.class);

    private ReentrantLock lock = null;
    private Condition condition = null;

    public ConditionTest(String name,ReentrantLock lock,Condition condition) {
        super(name);
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        lock.lock();
        try{
            LOGGER.info("thread name:{} lock success.",Thread.currentThread().getName());
            if(Thread.currentThread().getName().equals("test1")){
                condition.await();
                LOGGER.info("thread name:{} unlock.",Thread.currentThread().getName());
            }else if(Thread.currentThread().getName().equals("test2")) {
                condition.signal();
                LOGGER.info("thread name:{} unlock.",Thread.currentThread().getName());
            }
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }finally {
            lock.unlock();
            LOGGER.info("thread name:{} unlock success.",Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        ConditionTest test1 = new ConditionTest("test1",reentrantLock,condition);
        ConditionTest test2 = new ConditionTest("test2",reentrantLock,condition);
        test1.start();
        test2.start();
    }
}
