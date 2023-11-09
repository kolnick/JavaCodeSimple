package concurrent.thread.threadpool;

import lombok.Getter;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: caochaojie
 * @Date: 2023-11-08 20:28
 */
@Getter
public class ThreadNameFactory implements ThreadFactory {

    private ThreadGroup group;


    private AtomicInteger threadNumber = new AtomicInteger(0);

    private String namePrefix;

    private boolean daemon;

    public ThreadNameFactory(String namePreFix, boolean daemon) {
        group = Thread.currentThread().getThreadGroup();
        this.namePrefix = namePreFix + "-thread-";
        this.daemon = daemon;
    }

    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r, namePrefix
                + threadNumber.getAndIncrement(), 0);
        if (daemon) {
            t.setDaemon(daemon);
        } else {
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }

}
