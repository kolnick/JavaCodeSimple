package com.caochaojie.protostuff.codec;

import com.caochaojie.protostuff.message.ReqUpdateAccountMsg;
import com.caochaojie.protostuff.util.ProtostuffUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Author: caochaojie
 * @Date: 2019/5/14
 */
public class MessageDecoder extends ByteToMessageDecoder {
    private Class<?> genericClass;

    public MessageDecoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    public final void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 4) {
            return;
        }
        in.markReaderIndex();
        int dataLength = in.readInt();
        if (dataLength < 0) {
            ctx.close();
        }

        if (in.readableBytes() < dataLength - 2 * Integer.BYTES) {
            in.resetReaderIndex();
        }
        int msgId = in.readInt();
        if (msgId < 0) {
            ctx.close();
        }

        byte[] data = new byte[dataLength - 2 * Integer.BYTES];
        in.readBytes(data);
        Object obj = ProtostuffUtil.deserialize(data, ReqUpdateAccountMsg.class);
        out.add(obj);
    }

}
