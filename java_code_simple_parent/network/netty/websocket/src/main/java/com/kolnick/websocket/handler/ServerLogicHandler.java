package com.kolnick.websocket.handler;

import com.kolnick.websocket.message.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class ServerLogicHandler extends MessageToMessageDecoder<Message> {


    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, Message message, List<Object> list) throws Exception {
        byte[] byteBuf = message.getByteBuf();
        String s = new String(byteBuf, "UTF-8");
        System.out.printf(s);
    }


}
