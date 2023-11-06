package com.kolnick.demo.server;

import com.kolnick.demo.server.handler.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author caochaojie
 * 2018/11/15 9:33
 * @description
 */
public class FirstNettyServer {

    public static void main(String[] args) {

        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workGroup);
        serverBootstrap.channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);

        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                System.out.println(ch.remoteAddress());
                ch.pipeline().addLast(new LineBasedFrameDecoder(100));
                ch.pipeline().addLast(new StringDecoder());
                ch.pipeline().addLast(new ServerHandler());
                ch.pipeline().addLast(new StringEncoder());
            }
        });
        ChannelFuture future = null;
        try {
            future = serverBootstrap.bind(8888).sync();
            System.out.println("服务器启动成功");
            future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
