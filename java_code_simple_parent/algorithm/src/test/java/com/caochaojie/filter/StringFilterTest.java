package com.caochaojie.filter;


import org.testng.annotations.Test;

/**
 * @Author: caochaojie
 * @Date: 2019/4/30
 */
public class StringFilterTest {

    @Test
    public void test() {
        StringFilterUtil.load();
        boolean xx = StringFilterUtil.isLegal("我操啊");
        System.out.println(xx);
    }

}
