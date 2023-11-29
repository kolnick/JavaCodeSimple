package com.caochaojie.file;

import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Path;
import java.sql.SQLOutput;

/**
 * @Author: caochaojie
 * @Date: 2023-11-23 14:18
 */
public class FileTest {

    @Test
    public void pathTest() {
        System.out.println(File.separator);
        System.out.println(File.pathSeparator);
        System.out.println(File.pathSeparatorChar);
        System.out.println(File.separatorChar);

    }
}
