package com.kolnick.websocket.codec;

import com.kolnick.websocket.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class MessageDecoder extends LengthFieldBasedFrameDecoder {
    private static final int HEADER_SIZE = 4;


    public MessageDecoder() {
        this(1024 * 1024, 0, 4, -4, 0);
    }

    public MessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        in = (ByteBuf) super.decode(ctx, in);

        if (in == null) {
            return null;
        }
        if (in.readableBytes() < HEADER_SIZE) {
            throw new Exception("字节数不足");
        }
        int length = in.readInt();
        int msgId = in.readInt();
        short order = in.readShort();

        byte[] bytes = new byte[in.readableBytes()];
        in.readBytes(bytes);
        return new Message(length, msgId, order, bytes);
    }
}
