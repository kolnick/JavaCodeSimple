package io.file;

import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author caochaojie
 * @Date 2022/8/20
 */
public class PathTest {
    @Test
    public void createFile() throws IOException {
        String path = "d:/temp/aa/bb/cc";
        File file = new File(path);
        file.mkdirs();

        Path createFile = Paths.get(path + "/1.txt");
        Files.createFile(createFile);
    }
}
