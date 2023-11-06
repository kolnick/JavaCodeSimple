package com.caochaojie.middleware.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.List;

/**
 * Apache Curator的Watcher监听机制
 * <p>
 * 在zookeeper中，可以监听某个节点发生的特定事件，例如，监听节点数据变更、节点删除、子节点状态变更等事件。
 * 当相应事件发生时，zookeeper会产生一个watcher事件，并且发送至客户端。
 * <p>
 * 通过watcher机制，可实现分布式锁等功能。
 *
 * @author caochaojie
 */
public class ApacheCuratorWatcherTest {
    public static void main(String[] args) throws Exception {
        String zkAddress = "192.168.40.130:2181";
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zkAddress, retryPolicy);
        client.start();

        final String path = "/user";

        client.create()
                .withMode(CreateMode.PERSISTENT)
                .forPath(path, "sunchaser".getBytes());

        List<String> children = client.getChildren()
                .usingWatcher((CuratorWatcher) event -> {
                    // 只会触发一次
                    System.out.println("watched event: " + event);
                })
                .forPath(path);

        System.out.println(children);
        System.in.read();
    }
}
