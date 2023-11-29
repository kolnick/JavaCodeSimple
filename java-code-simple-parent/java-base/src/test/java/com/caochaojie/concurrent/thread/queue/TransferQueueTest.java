package com.caochaojie.concurrent.thread.queue;

import cn.hutool.core.util.RandomUtil;
import org.testng.annotations.Test;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * @Author: caochaojie
 * @Date: 2023-11-08 23:51
 */
public class TransferQueueTest {

    @Test
    public static void main(String[] args) throws InterruptedException {

        TransferQueue<Integer> queue = new LinkedTransferQueue<>();
// 生产者线程
        new Thread(() -> {
            try {
                while (true) {
                    int data = produceData(); // 生产数据
                    queue.transfer(data); // 将数据传递给消费者
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

// 消费者线程
        new Thread(() -> {
            try {
                while (true) {
                    System.out.println(queue.size());
                    Integer data = queue.take(); // 从队列中取出数据
                    processData(data); // 处理数据
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    private static void processData(Integer data) {
        String format = "消费者已接收到数据:" + data;
        System.out.println(format);
    }

    private static int produceData() {
        return RandomUtil.randomInt(100);
    }
}
