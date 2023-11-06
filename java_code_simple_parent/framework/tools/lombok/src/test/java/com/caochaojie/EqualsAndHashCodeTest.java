package com.caochaojie;

import lombok.EqualsAndHashCode;
import org.junit.Test;


@EqualsAndHashCode(exclude = {"ex"})
public class EqualsAndHashCodeTest {
    private String equal;
    private String hc;
    transient String tr;
    private String ex;

    @Test
    public void test() {

    }
}
