package com.fly.learn.futrue;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CompletableFuture实现Future、CompletionStage接口
 * 1. then 直译【然后】，也就是表示下一步，所以通常是一种串行关系体现, then 后面的单词（比如 run /apply/accept）就是上面说的函数式接口中的抽象方法名称了，它的作用和那几个函数式接口的作用是一样一样滴;
 * 2. combine... with... 和 both...and... 都是要求两者都满足，也就是 and 的关系了
 * 3. Either...or... 表示两者中的一个，自然也就是 Or 的体现了
 * 4. whenComplete 和 handle 的区别如果你看接受的参数函数式接口名称你也就能看出差别了，前者使用Comsumer, 自然也就不会有返回值；后者使用 Function，自然也就会有返回值
 *
 * @author: peijiepang
 * @date 2020/8/4
 * @Description:
 */
public class CompletableFutureTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(CompletableFutureTest.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // complete get 阻塞
        CompletableFuture<String> future = new CompletableFuture<>();
        future.complete("finish...");
        System.out.println("future:"+future.get());

        // runnable 没有返回值
        CompletableFuture<Void> test = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("test:"+test.get());

        // 带返回值
        CompletableFuture<String> test1 =  CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "结果值";
        });
        System.out.println("test1:"+test1.get());

        //对 thenApply 的调用并没有阻塞程序打印log，也就是前面说的通过回调通知机制， 这里你看到 thenApply  使用的是supplyAsync所用的线程，如果将supplyAsync 做快速返回，我们再来看一下运行结果：
        CompletableFuture<String> test2 = CompletableFuture.supplyAsync(()->{
            try {
                LOGGER.info("test2-----1");
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "1";
        }).thenApply((first)->{
            LOGGER.info("test2-----2");
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return first+"、2";
        }).thenApply((second)->{
            LOGGER.info("test2-----3");
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return second+"、3";
        });
        LOGGER.info("test2:"+test2.get());

    }

}
