package com.caochaojie;

import lombok.Data;
import org.testng.annotations.Test;


@Data(staticConstructor = "newInstance")
public class DataTest {
    private String s;
    private String c;

    @Test
    public void test() {
        DataTest dataTest = DataTest.newInstance();
        System.out.println(dataTest);
    }
}
