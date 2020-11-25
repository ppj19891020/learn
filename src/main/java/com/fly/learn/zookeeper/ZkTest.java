package com.fly.learn.zookeeper;

import java.io.IOException;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryForever;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author: peijiepang
 * @date 2020/11/25
 * @Description:
 */
public class ZkTest {

    public static void main(String[] args) throws InterruptedException, IOException {
        String connectionInfo = "192.168.201.178";
//        ZooKeeper zooKeeper = new ZooKeeper(connectionInfo,5000,null);

        RetryPolicy retryPolicy = new RetryForever(1000);
        CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString(connectionInfo)
            .sessionTimeoutMs(60000)
            .connectionTimeoutMs(10000)
            .retryPolicy(retryPolicy)
            .namespace("keygen")
            .build();
        client.start();

        Thread.sleep(Long.MAX_VALUE);
    }

}
