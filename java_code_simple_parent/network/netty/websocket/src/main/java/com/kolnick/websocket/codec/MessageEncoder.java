package com.kolnick.websocket.codec;

import com.kolnick.websocket.message.Message;
import com.kolnick.websocket.message.MessagePackage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MessageEncoder extends MessageToByteEncoder<Message> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Message message, ByteBuf byteBuf) throws Exception {
        ByteBuf byteBuf1 = MessagePackage.packageMessage(message);
        channelHandlerContext.channel().writeAndFlush(byteBuf1);
    }
}
