package com.caochaojie.datatype;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.text.MessageFormat;
import java.util.Set;

/**
 * @Author: caochaojie
 * @Date: 2023-11-04 22:53
 */
@Slf4j
public class StringTest {


    @Test
    public void testMessageFormat() {
        String test = MessageFormat.format("test {0} {1} dadad {2}", "test", 1, 2);
        System.out.println(test);
    }

    @Test
    public void lowerCase() {
        String str = "ADAD_DGGG";
        System.out.println(str.toLowerCase());
    }


    @Test
    public void testReplace() {
        String str = "card_number";
        str = str.toUpperCase().replace("_", "");
        System.out.println(str);
    }


}
