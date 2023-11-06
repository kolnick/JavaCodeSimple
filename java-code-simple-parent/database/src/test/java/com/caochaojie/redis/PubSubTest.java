package com.caochaojie.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

/**
 * @Author: caochaojie
 * @Date: 2023-11-06 19:26
 */
public class PubSubTest {
    private JedisPool jedisPool;

    private String host = "192.168.1.5";

    private int port = 6379;

    private int timeout = 5000;
    public static final String password = "";

    public static final int index = 1;

    @Before
    public void before() {
        jedisPool = redisPoolFactory();
    }

    public JedisPool redisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(10000);
        jedisPoolConfig.setMaxWaitMillis(5000);
        return new JedisPool(jedisPoolConfig, host, port);
        //   return new JedisPool(jedisPoolConfig, host, port, timeout, password, index);
    }

    @Test
    public void testRedisPubSub() throws InterruptedException {
        String channel = "my_channel";

        // 创建一个订阅者
        Thread subscriberThread = new Thread(() -> {
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.subscribe(new Subscriber(), channel);
            }
        });

        // 创建一个发布者
        Thread publisherThread = new Thread(() -> {
            try (Jedis jedis = jedisPool.getResource()) {
                for (int i = 0; i < 10; i++) {
                    jedis.publish(channel, "Message " + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 启动订阅者和发布者线程
        subscriberThread.start();
        publisherThread.start();

        // 等待一段时间，以确保订阅者能够接收到所有消息
        Thread.sleep(5000);
    }

    public static class Subscriber extends JedisPubSub {
        @Override
        public void onMessage(String channel, String message) {
            System.out.println("Received message: " + message + " from channel: " + channel);
        }
    }
}
