package concurrent.future;

import org.testng.annotations.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author: caochaojie
 * @Date: 2023-11-16 23:42
 */
public class CompletableFutureTest {

    @Test
    public void test() {
        // 创建异步任务1
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000); // 模拟耗时操作
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Result of Future 1";
        });

        // 创建异步任务2
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Result of Future 2");

        // 合并两个任务的结果
        CompletableFuture<String> combinedFuture = future1.thenCombine(future2, (result1, result2) -> result1 + ", " + result2);

        // 异步任务3，将结果打印到控制台
        combinedFuture.thenAcceptAsync(System.out::println);

        // 阻塞等待任务完成
        try {
            combinedFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
