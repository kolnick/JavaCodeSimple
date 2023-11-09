package concurrent.thread.worker;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author: caochaojie
 * @Date: 2023-11-08 20:52
 */

public class TasksQueue<V> {
    private final BlockingQueue<V> taskQueue = new LinkedBlockingQueue<>();


    private boolean processingCompleted = true;

    public V poll() {
        return taskQueue.poll();
    }

    public boolean add(V value) {
        return taskQueue.add(value);
    }

    public int size() {
        return taskQueue.size();
    }

    public boolean isProcessingCompleted() {
        return processingCompleted;
    }

    public void setProcessingCompleted(boolean processingCompleted) {
        this.processingCompleted = processingCompleted;
    }


}
