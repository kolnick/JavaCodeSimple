package com.caochaojie.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void set() {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();// 首先redisTemplate.opsForValue的目的就是表明是以key，value形式储存到Redis数据库中数据的
        ops.set("address1", "zhengzhou");// 到这里就表明Redis数据库中存储了key为address1，value为zhengzhou的数据了（取的时候通过key取数据）
    }

    /**
     * 取数据
     */
    @Test
    public void get() {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();  // 表明取的是key，value型的数据
        Object o = ops.get("address1");  // 获取Redis数据库中key为address1对应的value数据
        System.out.println(o);

    }


}
