package com.kolnick.websocket.handler;

import com.kolnick.websocket.message.Message;
import com.kolnick.websocket.message.MessagePackage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //     super.channelActive(ctx);
        System.out.println("已连接到服务器");

        Message message = new Message();
        String str = "hello";
        message.setByteBuf(str.getBytes("UTF-8"));
        message.setOrder((short) 5);
        message.setMsgId(1);
        message.setLength(4 + 6 + message.getByteBuf().length);
        ctx.write(MessagePackage.packageMessage(message));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //   super.channelRead(ctx, msg);
        System.out.println("收到服务器返回的消息" + msg);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //   super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}
