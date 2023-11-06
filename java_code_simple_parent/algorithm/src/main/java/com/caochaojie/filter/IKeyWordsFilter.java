package com.caochaojie.filter;

/**
 * @Author: caochaojie
 * @Date: 2019/4/30
 */
public interface IKeyWordsFilter {
    boolean initialize(String[] var1);

    String filt(String var1);

    boolean contain(String var1);
}
