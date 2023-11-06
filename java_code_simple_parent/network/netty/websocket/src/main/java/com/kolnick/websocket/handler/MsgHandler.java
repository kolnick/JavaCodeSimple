package com.kolnick.websocket.handler;

import io.netty.channel.ChannelHandlerContext;

public interface MsgHandler {

    void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception;

    // 有新客户端连接
    default void handlerAdded(ChannelHandlerContext ctx) throws Exception {
    }

    // 有客户端退出
    default void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    }

    // 有客户端出现异常
    default void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    }

}
