package com.caochaojie.genid;

import cn.hutool.core.util.IdUtil;
import org.junit.Test;

public class GenIdTest {
    @Test
    public void simpleUUID() {
        String s = IdUtil.fastSimpleUUID();
        System.out.println(s);
    }
}
