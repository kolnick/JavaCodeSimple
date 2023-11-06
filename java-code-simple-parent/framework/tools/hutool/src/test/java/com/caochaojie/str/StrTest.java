package com.caochaojie.str;

import cn.hutool.core.util.StrUtil;
import org.junit.Test;

import java.util.List;

/**
 * @author caochaojie
 * @date 2020/01/07
 */
public class StrTest {
    @Test
    public void test() {
        String str = "1|3:2";
        List<String> split = StrUtil.split(str, "|");
        for (String s : split) {
            System.out.println(s);
        }
    }
}
