package benchmark;

import bean.Bean;
import bean.BigObject;
import com.alibaba.fastjson2.JSON;
import org.apache.lucene.util.RamUsageEstimator;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author: caochaojie
 * @Date: 2023-11-17 0:26
 */
@BenchmarkMode(Mode.AverageTime) // 平均响应时间
@State(Scope.Thread)  // 每个进行基准测试的线程都会独享一个对象实例
@Fork(value = 1) // 开启一个线程进行测试
@OutputTimeUnit(TimeUnit.MILLISECONDS) // 输出时间单位
@Warmup(iterations = 1) // 微基准测试前进行三次预热执行，也可用在测试方法上。
public class JMHFastJson2Test {
    @Param({"-Xms8g -Xmx8g -XX:+UseG1GC"})
    public String jvmArgs;

    @Benchmark
    public void beanch(JMHFastJson2Test test) {
       // List<Integer> list = IntStream.range(0, 100000).boxed().collect(Collectors.toList());

        List<Map<Integer, Bean>> listMap = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Map<Integer, Bean> map1 = new HashMap<>();
            for (int j = 0; j < 100000; j++) {
                Bean bean = new Bean();
                bean.setAge(10);
                bean.setName("Alice");
                map1.put(j, bean);
            }
            listMap.add(map1);
        }
        BigObject bigObject = new BigObject("Alice", 25, null, listMap);
        String jsonString = JSON.toJSONString(bigObject);
        long l1 = System.currentTimeMillis();
        BigObject bigObject1 = JSON.parseObject(jsonString, BigObject.class);
        long l2 = System.currentTimeMillis();
        if (bigObject1 != null) {
            System.out.println(l2 - l1);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHFastJson2Test.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }
}
