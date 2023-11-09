package com.caochaojie.version;

import io.netty.util.Version;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * @Author: caochaojie
 * @Date: 2023-11-09 15:20
 */
public class VersionTest {
    @Test
    public void version() {
        Map<String, Version> identify = Version.identify();
        for (Map.Entry<String, Version> entry : identify.entrySet()) {
            System.out.println("key:" + entry.getKey());
            System.out.println("value:" + entry.getValue());
            System.out.println("");
        }
    }
}
