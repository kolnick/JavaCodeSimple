package com.caochaojie.test;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @Author: caochaojie
 * @Date: 2023-11-10 11:37
 */


@BenchmarkMode(Mode.AverageTime) // 平均响应时间
@State(Scope.Thread)  // 每个进行基准测试的线程都会独享一个对象实例
@Fork(value = 1) // 开启一个线程进行测试
@OutputTimeUnit(TimeUnit.MILLISECONDS) // 输出时间单位
@Warmup(iterations = 3) // 微基准测试前进行三次预热执行，也可用在测试方法上。
@Measurement(iterations = 5) // 进行 5 次微基准测试，也可用在测试方法上。
public class JmhTest {
    // JVM 参数和配置
    @Param({"-XX:+UseParallelGC", "-XX:+UseConcMarkSweepGC", "-XX:+UseG1GC"})
    public String jvmArgs;
    String string = "";
    StringBuilder stringBuilder = new StringBuilder();

    @Benchmark
    public String stringAdd(JmhTest jmhTest) {
        for (int i = 0; i < 1000; i++) {
            string = string + i;
        }
        return string;
    }

    @Benchmark
    public String stringBuilderAppend(JmhTest jmhTest) {
        for (int i = 0; i < 1000; i++) {
            stringBuilder.append(i);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JmhTest.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }
}
