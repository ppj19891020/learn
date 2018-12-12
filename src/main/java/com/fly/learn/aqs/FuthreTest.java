package com.fly.learn.aqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * 测试future
 * @author: peijiepang
 * @date 2018-12-11
 * @Description:
 */
public class FuthreTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(FuthreTest.class);
    private static ThreadPoolExecutor executorService = new ThreadPoolExecutor(1, 1, 1L, TimeUnit.MINUTES,
            new ArrayBlockingQueue<Runnable>(100),new ThreadPoolExecutor.DiscardPolicy());

    public static void main(String[] args) throws Exception {
        Future futureOne = executorService.submit(new Runnable() {
            @Override
            public void run() {
                LOGGER.info("start runable one");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Future futureTwo = executorService.submit(new Runnable() {
            @Override
            public void run() {
                LOGGER.info("start runable two");
            }
        });

        Future futureThree=null;
        try {
            futureThree = executorService.submit(new Runnable() {
                @Override
                public void run() {
                    LOGGER.info("start runable three");
                }
            });
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage());
        }

        Future<Integer> futureFour = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(5000L);
                return 1;
            }
        });

        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(5000L);
                return "ok";
            }
        });
        executorService.submit(futureTask);

        LOGGER.info("task one " + futureOne.get());
        LOGGER.info("task two " + futureTwo.get());
        LOGGER.info("task three " + (futureThree==null?null:futureThree.get()));
        LOGGER.info("task fore "+futureFour.get());
        LOGGER.info("futureTask "+futureTask.get());
        executorService.shutdown();
    }


}
