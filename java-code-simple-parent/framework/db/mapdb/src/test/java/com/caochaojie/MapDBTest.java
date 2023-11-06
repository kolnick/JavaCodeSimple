package com.caochaojie;

import org.junit.Test;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ConcurrentMap;

/**
 * Unit test for simple App.
 */
public class MapDBTest {


    private String storePath = "D:/temp/mapdb1.db";

    @Test
    public void test() throws IOException {
        Path PATH = Paths.get(storePath);
        Path file = Files.createFile(PATH);

        DB db = DBMaker
                .fileDB(storePath)
                .fileMmapEnable()
                .make();
        ConcurrentMap<String, Long> map = db
                .hashMap("map", Serializer.STRING, Serializer.LONG)
                .createOrOpen();
        System.out.println("a1:" + map.get("something"));

        db.close();
    }
}
