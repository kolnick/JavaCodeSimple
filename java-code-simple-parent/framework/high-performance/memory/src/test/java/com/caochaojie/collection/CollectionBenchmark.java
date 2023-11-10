package com.caochaojie.collection;

import org.agrona.collections.IntArrayList;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * @Author: caochaojie
 * @Date: 2023-11-10 20:07
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)

public class CollectionBenchmark {

    @Benchmark
    public void testIntArrayList() {
        IntArrayList list = new IntArrayList();
        for (int i = 0; i < 1000000; i++) {
            list.add(i);
        }
    }




}
