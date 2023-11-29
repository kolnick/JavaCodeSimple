package com.caochaojie.threadpool;

import concurrent.thread.threadpool.RejectReentrantWorkQueueHandler;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author caochaojie
 * 2022/7/25
 * @description
 */
@Slf4j
public class ReentrantThreadPoolTest {

    @Test
    public void reentrantThreadPool() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 3, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1), new RejectReentrantWorkQueueHandler());

        for (int i = 0; i < 10; i++) {
            executor.execute(new Task("线程" + i));
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Data
    static class Task implements Runnable {

        private String name;

        public Task(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            log.info("线程{}执行任务", name);
        }
    }
}
