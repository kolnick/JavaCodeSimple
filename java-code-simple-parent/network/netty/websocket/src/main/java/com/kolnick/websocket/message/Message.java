package com.kolnick.websocket.message;


public class Message {

    private int length;

    private int msgId;

    private short order;

    private byte[] byteBuf;

    public Message(int length, int msgId, short order, byte[] byteBuf) {
        this.length = length;
        this.msgId = msgId;
        this.order = order;
        this.byteBuf = byteBuf;
    }

    public Message() {

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

    public short getOrder() {
        return order;
    }

    public void setOrder(short order) {
        this.order = order;
    }

    public byte[] getByteBuf() {
        return byteBuf;
    }

    public void setByteBuf(byte[] byteBuf) {
        this.byteBuf = byteBuf;
    }
}
