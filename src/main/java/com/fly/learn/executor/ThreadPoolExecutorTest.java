package com.fly.learn.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池test
 * @author: peijiepang
 * @date 2018/11/23
 * @Description:
 */
public class ThreadPoolExecutorTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(ThreadPoolExecutorTest.class);
    //private static ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
    //private static Executor executor = Executors.newFixedThreadPool(5);
    //private static Executor executor = Executors.newSingleThreadExecutor();
    //private static Executor executor = Executors.newCachedThreadPool();
    private static ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);

    private AtomicInteger i = new AtomicInteger(0);

    public void executeTask(){
        Task1 task1 = new Task1();//构建任务
//        executor.execute(task1);//执行任务
        executor.scheduleAtFixedRate(task1,0,1,TimeUnit.SECONDS);
    }

    /*
     * 基本任务
     */
    class Task1 implements Runnable{
        public void run() {
            //具体任务的业务
            LOGGER.info("{}...",i.incrementAndGet());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutorTest test = new ThreadPoolExecutorTest();
        test.executeTask();
        Thread.sleep(100000L);
        executor.shutdown();
        while (!executor.isTerminated()){
            LOGGER.info("finish......");
        }
    }

}
