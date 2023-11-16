package com.caochaojie.executors;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author: caochaojie
 * @Date: 2023-11-16 23:25
 */
public class MoreExecutorsTest {

    /**
     * 可以用于创建一个固定大小的线程池，并在 JVM 关闭时自动关闭
     */
    @Test
    public void getExitingExecutorService() throws InterruptedException, ExecutionException {
        // 创建 ThreadPoolExecutor
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

        // 装饰成 ExitingExecutorService
        ExecutorService exitingExecutorService = MoreExecutors.getExitingExecutorService(
                executor,
                5, // 最大等待时间
                TimeUnit.SECONDS
        );

        // 提交任务
        exitingExecutorService.submit(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("Task completed");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 主线程休眠，等待线程池关闭
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 关闭 ExecutorService
        MoreExecutors.shutdownAndAwaitTermination(exitingExecutorService, 5, TimeUnit.SECONDS);
    }

    /**
     * 用于创建一个固定大小的调度线程池，并在 JVM 关闭时自动关闭。
     */
    @Test
    public void getExitingScheduledExecutorService() {
        ScheduledExecutorService scheduledExecutorService = MoreExecutors.getExitingScheduledExecutorService(
                (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(3)
        );
    }

    /**
     * 用于将普通的 ExecutorService 转换为 ListeningExecutorService，使其能够支持 Guava 的 ListenableFuture 和 FutureCallback。
     */
    @Test
    public void listeningDecorator() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);
    }

    /**
     * 用于优雅地关闭 ExecutorService，并等待指定的时间，确保所有任务完成。
     */
    @Test
    public void shutdownAndAwaitTermination() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        MoreExecutors.shutdownAndAwaitTermination(executorService, 10, TimeUnit.SECONDS);
    }

}
