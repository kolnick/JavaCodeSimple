package com.kolnick.websocket.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpCodecHandler extends ChannelInitializer<Channel> {

    private boolean client;

    public HttpCodecHandler(boolean client) {
        this.client = client;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        if (client) {
            channel.pipeline().addLast(new HttpClientCodec());
        } else {
            channel.pipeline().addLast(new HttpServerCodec());
        }

    }
}
