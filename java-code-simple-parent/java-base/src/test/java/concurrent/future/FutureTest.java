package concurrent.future;

import org.testng.annotations.Test;

import java.util.concurrent.*;

/**
 * @Author: caochaojie
 * @Date: 2023-11-16 23:38
 */
public class FutureTest {

    @Test
    public void test() throws ExecutionException, InterruptedException {
        // 创建 ExecutorService
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // 提交异步任务
        Future<Integer> future = executor.submit(() -> {
            System.out.println("Task is running...");
            Thread.sleep(2000);
            return 1 + 2;
        });

        // 主线程可以继续执行其他任务
        try {
            // 阻塞等待异步任务完成，并获取结果
            Integer result = future.get();
            System.out.println("Result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            // 关闭 ExecutorService
            executor.shutdown();
        }
    }
}
