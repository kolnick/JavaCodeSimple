package com.caochaojie.protostuff.client;

import com.caochaojie.protostuff.codec.MessageDecoder;
import com.caochaojie.protostuff.codec.MessageEncoder;
import com.caochaojie.protostuff.handler.MyClientHandler;
import com.caochaojie.protostuff.message.AccountInfoVO;
import com.caochaojie.protostuff.message.IMessage;
import com.caochaojie.protostuff.message.ReqUpdateAccountMsg;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: caochaojie
 * @Date: 2019/5/14
 */
public class MyClient {
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8080"));
    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));

    public static void main(String[] args) throws Exception {

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
                            ChannelPipeline p = ch.pipeline();
                            ch.pipeline().addLast(new MessageEncoder(IMessage.class));
                            ch.pipeline().addLast(new MessageDecoder(IMessage.class));
                            ch.pipeline().addLast(new MyClientHandler());
                        }
                    });

            ChannelFuture future = b.connect(HOST, PORT).sync();
            ReqUpdateAccountMsg myRequest = new ReqUpdateAccountMsg();
            AccountInfoVO accountInfoVO = new AccountInfoVO();
            accountInfoVO.setAccountId(1);
            accountInfoVO.setLevel(2);
            myRequest.setId(1);
            myRequest.setAccountInfoVO(accountInfoVO);
            List<AccountInfoVO> list = new ArrayList<>();
            list.add(accountInfoVO);
            myRequest.setList(list);
            future.channel().writeAndFlush(myRequest);
            future.channel().writeAndFlush(myRequest);
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
