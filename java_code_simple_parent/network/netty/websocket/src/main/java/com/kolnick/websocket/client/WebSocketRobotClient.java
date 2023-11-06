package com.kolnick.websocket.client;

import com.kolnick.websocket.codec.ProtobufHeadEncoder;
import com.kolnick.websocket.handler.ClientWebSocketHandler;
import com.kolnick.websocket.message.Message;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;

import java.net.URI;
import java.util.List;

public class WebSocketRobotClient {
    private ChannelFuture cf = null;

    private static final ChannelOutboundHandler webSocketFrameEncoder = new MessageToMessageEncoder<ByteBuf>() {
        @Override
        protected void encode(ChannelHandlerContext ctx, ByteBuf msg,
                              List<Object> out) throws Exception {
            out.add(new BinaryWebSocketFrame(msg.retain()));
        }
    };

    public static void main(String[] args) {
        try {
            open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void open() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap boot = new Bootstrap();
        boot.option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new ChannelHandler[]{new HttpClientCodec(),
                                new HttpObjectAggregator(1024 * 1024 * 10)});
                        pipeline.addLast("frameEncoder", webSocketFrameEncoder);
                        pipeline.addLast("headEncoder",
                                new ProtobufHeadEncoder());
//                         pipeline.addLast("encoder", new ProtobufEncoder());
                        // and then business logic.
                        pipeline.addLast("hookedHandler", new ClientWebSocketHandler());
                    }
                });
        String SERVER_URI = "ws://192.168.0.152:10000/tt";

        URI websocketURI = new URI(SERVER_URI);
        HttpHeaders httpHeaders = new DefaultHttpHeaders();
        //进行握手
        WebSocketClientHandshaker handshaker = WebSocketClientHandshakerFactory.newHandshaker(websocketURI, WebSocketVersion.V13, (String) null, true, httpHeaders);
        System.out.println("connect");
        ChannelFuture connect = boot.connect(websocketURI.getHost(), websocketURI.getPort());
        ChannelFuture cf = connect.sync();
        final Channel channel = cf.channel();
        ClientWebSocketHandler handler = (ClientWebSocketHandler) channel.pipeline().get("hookedHandler");
        handler.setHandshaker(handshaker);
        handshaker.handshake(channel);
        //阻塞等待是否握手成功
        handler.handshakeFuture().sync();
        Message message = new Message();
        message.setMsgId(1);
        message.setOrder((short) 1);
        String str = "hello";
        message.setByteBuf(str.getBytes("UTF-8"));
        cf.channel().writeAndFlush(message);
    }

    public void close() {
        this.cf.channel().close();
    }
}
