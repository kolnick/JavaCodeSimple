package com.caochaojie.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author caochaojie
 * @date 2020/04/11
 */
public class RedisTest {
    private JedisPool jedisPool;

    private String host = "192.168.1.5";

    private int port = 6379;

    private int timeout = 3000;
    public static final String password = "";

    public static final int index = 0;

    @Before
    public void before() {
        jedisPool = redisPoolFactory();
    }

    public JedisPool redisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(10000);
        jedisPoolConfig.setMaxWaitMillis(5000);
        return new JedisPool(jedisPoolConfig, host, port);
        // return new JedisPool(jedisPoolConfig, host, port, timeout, password, index);
    }

    public Jedis getConnection() {
        return jedisPool.getResource();
    }

    @Test
    public void testSet() {
        Jedis connection = getConnection();
        // 添加
        connection.sadd("arr", "1");
        connection.sadd("arr", "2");
        connection.sadd("arr", "3");
        // 获取集合
        Set<String> smembers = connection.smembers("arr");
        System.out.println(smembers);
        // 删除
        connection.srem("arr", "1", "2");
        System.out.println(connection.smembers("arr"));// 获取所有加入的value

        connection.close();
    }


    @Test
    public void testList() {
        Jedis jedis = getConnection();
        // 开始前，先移除所有的内容
        jedis.del("java framework");
        jedis.lpush("java framework", "spring");
        jedis.lpush("java framework", "struts");
        jedis.lpush("java framework", "hibernate");
        // 键 开始位置,结束位置
        System.out.println(jedis.lrange("java framework", 0, -1));

        jedis.del("java framework");
        jedis.rpush("java framework", "spring");
        jedis.rpush("java framework", "struts");

        System.out.println(jedis.lrange("java framework", 0, -1));
    }

    @Test
    public void testRedis() {
        Jedis redis = getConnection();
        // 存储字符串
        redis.set("kolnick", "123");
        // 获取
        System.out.println(redis.get("kolnick"));
        // 拼接
        redis.append("kolnick", "wudi");
        System.out.println(redis.get("kolnick"));
        // 删除
        redis.del("kolnick");
        System.out.println(redis.get("kolnick"));
        // 设置与获取多个键值对
        redis.mset("aa", "11", "bb", "22", "cc", "33");
        System.out.println(redis.mget("aa", "bb", "cc"));
        redis.set("age", "1");
        // 加1操作
        redis.incr("age");
        System.out.println(redis.get("age"));
        // map操作
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "xinxin");
        map.put("age", "22");
        map.put("qq", "123456");
        redis.hmset("user", map);
        System.out.println(redis.hmget("user", "qq", "age", "name"));
        // 删除map中某个键值
        redis.hdel("user", "qq");
        System.out.println(redis.hmget("user", "qq"));
        // 返回key为user的键中存放的值的个数2
        System.out.println(redis.hlen("user"));
        // 是否存在key为user的记录 返回true
        System.out.println(redis.exists("user"));
        // 返回map对象中的所有key
        System.out.println(redis.hkeys("user"));
        // 返回map对象中的所有value
        System.out.println(redis.hvals("user"));
        Iterator<String> iter = redis.hkeys("user").iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            System.out.println(key + ":" + redis.hmget("user", key));
        }
    }


}
