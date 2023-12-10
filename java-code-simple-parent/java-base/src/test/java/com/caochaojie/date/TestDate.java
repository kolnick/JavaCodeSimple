package com.caochaojie.date;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * @author caochaojie
 * 2022/9/13
 * @description
 */
@Slf4j
public class TestDate {

    @Test
    public void timezone() {
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        System.out.println(timeZone);
        TimeZone timeZone1 = TimeZone.getTimeZone("GMT+08:00");
        System.out.println(timeZone1);
        System.out.println(timeZone1.getRawOffset());
        int i = 60 * 60 * 8;
        System.out.println(i);
        long time = 28800000 / 1000;
        System.out.println(time);
    }

    @Test
    public void timeUnit() {
        long time = TimeUnit.HOURS.toMillis(1);
        Assert.assertEquals(time, 60 * 60 * 1000);
    }

    @Test
    public void format() {
        String s = DateUtil.formatLocalDateTime(LocalDateTime.now());
        System.out.println(s);
    }

    @Test
    public void test() {

    }
}
