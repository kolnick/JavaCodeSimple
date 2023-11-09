package concurrent.thread.executor;

import org.testng.annotations.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: caochaojie
 * @Date: 2023-11-09 15:38
 */
public class ExecutorTest {

    @Test
    public void test() {
        // 创建一个dispatch线程池
        ExecutorService executor = Executors.newCachedThreadPool();
        // 定义任务
        Runnable task = () -> {
            // 任务逻辑
            System.out.println("hello world");
        };
        // 提交任务
        executor.execute(task);
        // 关闭线程池
        executor.shutdown();
    }
}
