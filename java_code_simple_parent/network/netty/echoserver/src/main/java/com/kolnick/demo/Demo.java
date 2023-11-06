package com.kolnick.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class Demo {

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                        }
                    });

            ChannelFuture sync = serverBootstrap.bind(18080).sync();
            sync.channel().closeFuture().addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    System.out.println(channelFuture.channel().toString() + "链路关闭");
                    bossGroup.shutdownGracefully();
                    workGroup.shutdownGracefully();
                }
            });
            //链路关闭触发closeFuture的监听，等待服务关闭之后异步调用优雅释放资源，这样线程就不会阻塞
            sync.channel().closeFuture();

        } finally {
            //这里将操作放在监听器中

          /*  bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();*/
        }
    }

}
