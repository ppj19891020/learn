package com.fly.learn.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: peijiepang
 * @date 2018/11/6
 * @Description:
 */
public class SynchronizedDemo {

    private final static Logger LOGGER = LoggerFactory.getLogger(SynchronizedDemo.class);

    public void test1(){
        synchronized (this){
            LOGGER.info("synchronized this1");
        }
    }

    public synchronized void test2(){
        LOGGER.info("synchronized this2");
    }

    public void test3(){
        synchronized (SynchronizedDemo.class){
            LOGGER.info("synchronized this2");
        }
    }

}
