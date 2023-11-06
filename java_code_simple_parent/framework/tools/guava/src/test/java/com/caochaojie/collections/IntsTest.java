package com.caochaojie.collections;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author caochaojie
 * 2022/7/1
 * @description
 */
@Slf4j
public class IntsTest {

    @Test
    public void max() {
        int[] ts = {1, 2, 3, 4};
        int max = com.google.common.primitives.Ints.max(ts);
        log.info("max:{}",max);
    }
}
