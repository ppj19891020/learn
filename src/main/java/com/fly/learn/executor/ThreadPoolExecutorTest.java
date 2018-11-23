package com.fly.learn.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    private static ExecutorService executor = Executors.newScheduledThreadPool(5);

    public void executeTask(){
        Task1 task1 = new Task1();//构建任务
        executor.execute(task1);//执行任务
    }

    /*
     * 基本任务2
     */
    class Task1 implements Runnable{
        public void run() {
            //具体任务的业务
            for(int i=0;i<1000;i++){
                LOGGER.info("{}...",i);
            }
        }
    }
    public static void main(String[] args) {
        ThreadPoolExecutorTest test = new ThreadPoolExecutorTest();
        test.executeTask();
        executor.shutdown();
        while (!executor.isTerminated()){
            LOGGER.info("finish......");
        }
    }

}
