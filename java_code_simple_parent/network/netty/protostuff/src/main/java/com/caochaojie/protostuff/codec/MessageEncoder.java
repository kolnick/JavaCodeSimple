package com.caochaojie.protostuff.codec;

import com.caochaojie.protostuff.message.IMessage;
import com.caochaojie.protostuff.message.MessagePackage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Author: caochaojie
 * @Date: 2019/5/14
 */
public class MessageEncoder extends MessageToByteEncoder {

    private Class<?> genericClass;

    public MessageEncoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    public void encode(ChannelHandlerContext ctx, Object in, ByteBuf out) throws Exception {
        if (genericClass.isInstance(in)) {
            IMessage message = (IMessage) in;
            ByteBuf byteBuf = MessagePackage.packageMsg(message);
            out.writeBytes(byteBuf);
        }
    }
}
