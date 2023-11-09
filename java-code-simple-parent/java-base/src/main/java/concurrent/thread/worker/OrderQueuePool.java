package concurrent.thread.worker;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: caochaojie
 * @Date: 2023-11-08 20:55
 */
public class OrderQueuePool<K, V> {

    Map<K, TasksQueue<V>> map = new ConcurrentHashMap<>();


    public TasksQueue<V> getTaskQueue(K key) {
        TasksQueue<V> queue = map.get(key);

        if (queue == null) {
            TasksQueue<V> newQueue = new TasksQueue<V>();
            queue = map.putIfAbsent(key, newQueue);
            if (queue == null) {
                queue = newQueue;
            }
        }
        return queue;
    }
}
