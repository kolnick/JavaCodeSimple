package com.kolnick.websocket.client;

import com.kolnick.websocket.handler.ClientHandler;
import com.kolnick.websocket.message.Message;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


public class Client {
    static final String URL = System.getProperty("url", "ws://127.0.0.1:10000/tt");

    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "10000"));
    private static final ChannelOutboundHandler webSocketFrameEncoder = new MessageToMessageEncoder<ByteBuf>() {
        @Override
        protected void encode(ChannelHandlerContext ctx, ByteBuf msg,
                              List<Object> out) throws Exception {
            out.add(new BinaryWebSocketFrame(msg.retain()));
        }
    };

    public static void main(String[] args) {
        // Configure the client.
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(webSocketFrameEncoder);
                            pipeline.addLast(new HttpClientCodec());
                            pipeline.addLast(new HttpObjectAggregator(9182));
                            pipeline.addLast(new ClientHandler());
                        }
                    });

            ChannelFuture future = null;
            try {
                URI uri = new URI("ws://192.168.0.152:10000/tt");
                future = b.connect(uri.getHost(), uri.getPort()).sync();
                String str = "hello world";
                Message message = new Message();
                message.setMsgId(1);
                message.setOrder((short) 1);
                message.setByteBuf(str.getBytes("UTF-8"));
                future.channel().writeAndFlush(message);
                future.channel().writeAndFlush(message);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
