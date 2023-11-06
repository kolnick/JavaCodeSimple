package com.caochaojie.springboot;

import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

public class TestGetResourcePath {

    @Test
    public void getClassPath() throws FileNotFoundException {
        String path = ResourceUtils.getURL("classpath:").getPath();
        System.out.println(path);
    }

}
