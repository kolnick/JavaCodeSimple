package com.kolnick.websocket.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class ClientMessageDecoder extends MessageToMessageDecoder<ProtoMessageHolder> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ProtoMessageHolder msg,
                          List<Object> out) throws Exception {
        System.out.println(out);
    }

}
