package com.caochaojie;

import cn.hutool.core.date.StopWatch;
import org.junit.Test;

/**
 * @author caochaojie
 * 2022/9/28
 * @description
 */
public class StopWatchTest {

    @Test
    public void stopWatch() throws InterruptedException {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("ddd");
        Thread.sleep(1000);
        stopWatch.stop();
        long lastTaskTimeMillis = stopWatch.getLastTaskTimeMillis();
        System.out.println(lastTaskTimeMillis);
    }

}
