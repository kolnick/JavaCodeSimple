package com.kolnick.demo.util;

import com.alibaba.fastjson.JSON;
import com.kolnick.demo.CallBackCommand;
import com.kolnick.demo.client.SessionManager;
import com.kolnick.demo.meta.Message;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author caochaojie
 * 2018/11/15 13:41
 * @description
 */
public class MessageUtil {
    private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(2, new ThreadFactory() {

        AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            int curCount = count.incrementAndGet();
            return new Thread(r, "异步回调（线程池）-" + curCount);
        }
    });

    public static void sendMessage(Message message, CallBackCommand command) {
        String s = JSON.toJSONString(message);
        s += "\r\n";
        ByteBuf buffer = Unpooled.buffer(s.length());
        buffer.writeBytes(s.getBytes());
        ChannelFuture channelFuture = SessionManager.getInstance().getClientChannel().writeAndFlush(buffer);
        channelFuture.addListener((ChannelFutureListener) future -> {
            if (future.isSuccess()) {
                if (command != null) {
                    executor.execute(command);
                }
            }
        });

    }

    public static void sendMessage(Message message) {
        sendMessage(message, null);
    }
}
