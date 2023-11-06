package com.kolnick.websocket.message;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public class MessagePackage {

    public static ByteBuf packageMessage(Message message) {

        int msgId = message.getMsgId();
        short order = message.getOrder();
        byte[] byteBuf = message.getByteBuf();
        int length = byteBuf.length;
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(length);
        buf.writeInt(10 + length);
        buf.writeInt(msgId);
        buf.writeShort(order);
        buf.writeBytes(byteBuf);
        return buf;
    }
}
