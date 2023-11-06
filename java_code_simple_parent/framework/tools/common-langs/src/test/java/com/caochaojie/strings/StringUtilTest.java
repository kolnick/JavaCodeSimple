package com.caochaojie.strings;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @author caochaojie
 * 2023/1/5
 * @description
 */
public class StringUtilTest {
    @Test
    public void testJoin() {

        String join = StringUtils.join(str(), "|");
        System.out.println(join);
    }

    private String[] str() {
        return new String[]{
                "1", "2", "3"
        };
    }
}
