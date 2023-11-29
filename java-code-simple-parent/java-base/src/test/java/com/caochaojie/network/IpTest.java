package com.caochaojie.network;

import lombok.extern.slf4j.Slf4j;
import network.IPUtil;
import org.junit.Test;

/**
 * @author caochaojie
 * 2022/8/3
 * @description
 */
@Slf4j
public class IpTest {

    @Test
    public void test() {
        log.info("{}", IPUtil.ip2Long("192.168.0.1"));
        log.info("{}", IPUtil.long2Ip(3232235521L));
        long ipLong = IPUtil.ip2Long("10.0.0.1");
        log.info("{}", ipLong);
        String ipStr = IPUtil.long2Ip(ipLong);
        log.info("{}", ipStr);
    }
}
