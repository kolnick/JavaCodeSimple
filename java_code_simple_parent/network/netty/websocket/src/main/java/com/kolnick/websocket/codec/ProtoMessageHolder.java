package com.kolnick.websocket.codec;

import io.netty.buffer.ByteBuf;

public class ProtoMessageHolder {
    int length;
    int msgId;
    ByteBuf msgBody;

    public ProtoMessageHolder(int length, int msgId, ByteBuf msgBody) {
        this.length = length;
        this.msgId = msgId;
        this.msgBody = msgBody;
    }

    @Override
    public String toString() {
        return "msgId=" + msgId + "  length=" + length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public ByteBuf getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(ByteBuf msgBody) {
        this.msgBody = msgBody;
    }
}
