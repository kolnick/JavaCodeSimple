package com.caochaojie.redisson;

import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

import java.io.ObjectInputFilter;


public class RedissonTest {

    @Test
    public void connection() {
        Config config = new Config();
        ClusterServersConfig clusterServersConfig = config.useClusterServers().addNodeAddress("com.caochaojie.redis://192.168.0.242:6379");
        clusterServersConfig.setPassword("fa114b13af242f0ccbf93cf07aecc74d");
        RedissonClient redissonClient = Redisson.create(config);
        RMap<Object, Object> anyMap = redissonClient.getMap("anyMap");
        System.out.println();
    }

    @Test
    public void singleServer() {
        Config config = new Config();
        SingleServerConfig singleSerververConfig = config.useSingleServer();
        singleSerververConfig.setAddress("com.caochaojie.redis://192.168.0.242:6379");
        singleSerververConfig.setPassword("fa114b13af242f0ccbf93cf07aecc74d");
        singleSerververConfig.setDatabase(1);
        RedissonClient redissonClient = Redisson.create(config);
    }

}
