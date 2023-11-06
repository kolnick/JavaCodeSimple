package concurrent.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author caochaojie
 * 2022/7/25
 * @description
 */
@Slf4j
public class RejectReentrantWorkQueueHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        log.info("有线程被拒绝了,当前线程是{}", Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executor.execute(r);
        log.info("线程已被重新放入到线程池当前池中的任务数{}", executor.getPoolSize());
    }
}
