package com.caochaojie.regex;

import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author caochaojie
 * @Date 2022/8/9
 */
public class RegexTest {

    @Test
    public void test() {
        String dd="12\t3\re23232\ntttWe";
        Pattern pattern = Pattern.compile("\t|\r|\n");
        Matcher matcher = pattern.matcher(dd);
        String s = matcher.replaceAll("");
        System.out.println(s);
    }

    @Test
    public void testNumber() {
        String dd="12\t3\re23232\ntttWe";
        Pattern pattern = Pattern.compile("\t|\r|\n");
        Matcher matcher = pattern.matcher(dd);
        String s = matcher.replaceAll("");
        System.out.println(s);
    }
}
