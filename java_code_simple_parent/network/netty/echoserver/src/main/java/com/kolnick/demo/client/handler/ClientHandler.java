package com.kolnick.demo.client.handler;

import com.alibaba.fastjson.JSON;
import com.kolnick.demo.Protocal;
import com.kolnick.demo.StatusCode;
import com.kolnick.demo.Worker;
import com.kolnick.demo.client.SessionManager;
import com.kolnick.demo.meta.Message;
import com.kolnick.demo.meta.Response;
import com.kolnick.demo.util.MessageUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author caochaojie
 * 2018/11/15 9:47
 * @description
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {


    public ClientHandler() {
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String msg = "hello world";
        SessionManager.getInstance().setClientChannel(ctx.channel());
        Message message = new Message();
        int i = 0;
        message.setMsg(msg + ":" + String.valueOf(i));
        if (i == 0) {
            message.setProtocol(Protocal.SIMPLE_MESSAGE1);
        } else if (i > 0) {
            message.setProtocol(Protocal.SIMPLE_MESSAGE2);
        }
        MessageUtil.sendMessage(message, () -> {
            Worker worker = Worker.getInstance();

            Lock lock = worker.getLock();
            Condition condition = worker.getCondition();
            try {
                lock.lock();
                condition.await();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

            System.out.println("执行回调:+ssss");

            Condition shutDownCondition = worker.getShutDownCondition();
            Lock shutDownLock = worker.getShutDownLock();
            try {
                shutDownLock.lock();
                shutDownCondition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                shutDownLock.unlock();
            }
        });

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            if (msg == null) {
                return;
            }
            String s = msg.toString();
            Response response = JSON.parseObject(s, Response.class);
            if (response != null) {
                int code = response.getCode();
                Object[] result = response.getResult();
                if (code == StatusCode.SUCCESS) {
                    if (result != null) {
                        Worker work = Worker.getInstance();
                        Lock lock = work.getLock();
                        try {
                            lock.lock();
                            System.out.println(System.currentTimeMillis() + "接收到来自服务器消息:" + response);
                            work.getCondition().signal();
                        } finally {
                            lock.unlock();
                        }
                    }
                } else {
                    System.out.println("解析服务器数据失败");
                }
            } else {
                System.out.println("解析服务器数据失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("解析服务器数据失败");
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
        System.out.println(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
        System.out.println("链接已断开" + ctx.channel());
        ctx.close();
    }
}
