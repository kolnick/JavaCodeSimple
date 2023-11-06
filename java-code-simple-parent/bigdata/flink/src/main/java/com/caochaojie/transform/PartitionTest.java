package com.caochaojie.transform;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class PartitionTest {
    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        executionEnvironment.setParallelism(4);

        DataStreamSource<String> dataStream = executionEnvironment.readTextFile("E:\\workspace\\code\\java_study_code\\flink\\src\\main\\resources\\1.txt");

        dataStream.print();

        DataStream<String> shuffleStream = dataStream.shuffle();

        shuffleStream.print("shuffle");

        dataStream .global().print();

        executionEnvironment.execute();
    }
}
