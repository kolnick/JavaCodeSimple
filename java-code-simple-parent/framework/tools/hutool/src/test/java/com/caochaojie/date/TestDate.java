package com.caochaojie.date;

import cn.hutool.core.date.CalendarUtil;
import cn.hutool.core.date.DateUtil;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * @Author: caochaojie
 * @Date: 2021/1/4
 */
public class TestDate {

    @Test
    public void test() {

        Calendar instance = Calendar.getInstance();
        Calendar calendar = CalendarUtil.endOfQuarter(instance);
        String format = DateUtil.format(new Date(calendar.getTimeInMillis()), "yyyy-MM-dd hh:MM:ss");
        System.out.println(format);
    }
}
