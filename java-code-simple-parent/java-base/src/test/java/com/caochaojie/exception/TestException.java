package com.caochaojie.exception;

import exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author caochaojie
 * 2022/8/5
 */
@Slf4j
public class TestException {


    @Test
    public void testException() {

        try {
            throwException();
        } catch (Exception e) {
            if (e instanceof BizException) {
                log.error("BizException", e);
            } else {
                log.error("Exception");
            }
        }

    }

    public void throwException() {
        RuntimeException e = new BizException("dada");
        throw e;
    }
}
