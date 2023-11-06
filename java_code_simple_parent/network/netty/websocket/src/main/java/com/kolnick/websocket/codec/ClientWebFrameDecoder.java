package com.kolnick.websocket.codec;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;

import java.util.List;

public class ClientWebFrameDecoder extends MessageToMessageDecoder {

    static final int HEAD_SIZE = 4;
    static final int MSG_TYPE_SIZE = 4;
    static final int MAX_PACAKGE_SIZE = 1024 * 1024;


    @Override
    protected void decode(ChannelHandlerContext ctx, Object obj, List list) throws Exception {
        if (obj instanceof BinaryWebSocketFrame) {
            BinaryWebSocketFrame frame = (BinaryWebSocketFrame) obj;
            ByteBuf in = frame.content();
            while (true) {
                if (in.readableBytes() < HEAD_SIZE) {
                    break;
                }
                in.markReaderIndex();
                int dataLength = in.readInt();

                if (dataLength < 0 || dataLength > MAX_PACAKGE_SIZE) {

                    ctx.close();
                    break;

                }
                if (in.readableBytes() < dataLength - HEAD_SIZE) {
                    //System.out.println("too short for the  rest msg");
                    in.resetReaderIndex();
                    break;
                }
                int msgId = in.readInt();
                int buffLen = dataLength - HEAD_SIZE - MSG_TYPE_SIZE;

                list.add(new ProtoMessageHolder(buffLen, msgId, in.readBytes(buffLen)));

            }
        }
    }
}
