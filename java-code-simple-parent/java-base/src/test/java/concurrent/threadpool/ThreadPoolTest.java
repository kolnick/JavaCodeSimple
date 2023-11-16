package concurrent.threadpool;

import org.testng.annotations.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: caochaojie
 * @Date: 2023-11-09 15:40
 */
public class ThreadPoolTest {

    @Test
    public void test() {
        //创建线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2, // corePoolSize - 核心线程数
                4, // maximumPoolSize - 最大线程数
                60, // keepAliveTime - 空闲线程存活时间
                TimeUnit.SECONDS, // 时间单位
                new ArrayBlockingQueue<Runnable>(4) // 阻塞队列
        );

        // 创建并提交任务
        for (int i = 0; i < 8; i++) {
            executor.execute(new Task("" + i));
        }

        // 关闭线程池
        executor.shutdown();
    }

    // 任务类
    class Task implements Runnable {

        private String name;

        public Task(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name + " task start");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " task finished");
        }
    }
}
