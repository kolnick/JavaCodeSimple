package com.kolnick.demo.server.handler;

import com.alibaba.fastjson.JSON;

import com.kolnick.demo.CommandFactory;
import com.kolnick.demo.meta.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author caochaojie
 * 2018/11/15 9:43
 * @description
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            if (msg instanceof String) {
                String str = (String) msg;
                System.out.println(System.currentTimeMillis() + "接收到来自客户端消息:" + str);
                Message message = JSON.parseObject(str, Message.class);
                if (message != null) {
                    CommandFactory.getInstance().executeCommand(ctx.channel(), message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
