package com.caochaojie.protostuff.message;

import com.caochaojie.protostuff.util.ProtostuffUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @Author: caochaojie
 * @Date: 2019/5/14
 */
public class MessagePackage {

    public static ByteBuf packageMsg(IMessage msg) {
        byte[] content = ProtostuffUtil.serialize(msg);
        int length = 2 * Integer.BYTES + content.length;
        msg.setLength(length);
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(length);
        buffer.writeInt(length);
        buffer.writeInt(msg.getId());
        buffer.writeBytes(content);
        return buffer;
    }
}
