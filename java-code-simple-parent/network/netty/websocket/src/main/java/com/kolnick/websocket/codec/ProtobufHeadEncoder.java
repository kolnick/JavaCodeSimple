package com.kolnick.websocket.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ProtobufHeadEncoder extends
        MessageToByteEncoder<ProtoMessageHolder> {
    static final int HEAD_SIZE = 4;
    static final int MSG_TYPE_SIZE = 4;

    @Override
    protected void encode(ChannelHandlerContext ctx, ProtoMessageHolder msg,
                          ByteBuf out) throws Exception {
        //System.out.println("###3 headerEncode  msgId="+msg.getMsgId());
//		LogUtil.USER.warn("ProtobufHeadEncoder  msgId="+msg.getMsgId());
        ByteBuf msgBody = msg.getMsgBody();
        int bodyLen = msgBody.readableBytes();
        int length = bodyLen + HEAD_SIZE + MSG_TYPE_SIZE;
        out.writeInt(length);// 4
        out.writeInt(msg.getMsgId());// 4
        out.writeBytes(msgBody);// length
    }

}
