package com.caochaojie;

import com.baomidou.mybatisplus.core.toolkit.sql.StringEscape;
import org.junit.Test;

/**
 * @author caochaojie
 * 2022/11/28
 * @description
 */
public class TestLike {

    @Test
    public void testLike() {
        String str = "'";
        String s = StringEscape.escapeString(str);
        System.out.println(s);
    }

}
