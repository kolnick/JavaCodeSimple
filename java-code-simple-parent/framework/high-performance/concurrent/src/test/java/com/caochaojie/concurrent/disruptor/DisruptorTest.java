package com.caochaojie.concurrent.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.Executors;

/**
 * @Author: caochaojie
 * @Date: 2023-11-21 15:35
 */
public class DisruptorTest {
    // 创建事件类
    static class Event {
        private String message;

        public void setMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    // 创建事件处理器
    static class EventProcessor implements EventHandler<Event> {
        @Override
        public void onEvent(Event event, long sequence, boolean endOfBatch) {
            System.out.println("Consumed: " + event.getMessage());
        }
    }

    // 创建RingBuffer的工厂
    static class EventFactory implements com.lmax.disruptor.EventFactory<Event> {
        @Override
        public Event newInstance() {
            return new Event();
        }
    }

    // 事件生产者
    static class EventProducer {
        private final RingBuffer<Event> ringBuffer;

        public EventProducer(RingBuffer<Event> ringBuffer) {
            this.ringBuffer = ringBuffer;
        }

        public void produce(String message) {
            long sequence = ringBuffer.next();
            try {
                Event event = ringBuffer.get(sequence);
                event.setMessage(message);
            } finally {
                ringBuffer.publish(sequence);
            }
        }
    }

    public static void main(String[] args) {

        // 创建Disruptor
        Disruptor<Event> disruptor = new Disruptor<>(new EventFactory(), 1024, Executors.defaultThreadFactory());

        // 连接事件处理器
        disruptor.handleEventsWith(new EventProcessor());

        // 启动Disruptor
        RingBuffer<Event> ringBuffer = disruptor.start();

        // 生产事件
        EventProducer eventProducer = new EventProducer(ringBuffer);
        eventProducer.produce("Hello Disruptor!");

        // 关闭Disruptor
        disruptor.shutdown();
    }

}
