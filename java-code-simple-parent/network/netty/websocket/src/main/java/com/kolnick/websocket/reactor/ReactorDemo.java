package com.kolnick.websocket.reactor;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

/**
 * @Author: caochaojie
 * @Date: 2021/4/23
 */
public class ReactorDemo {


    public static void single() {
        EventLoopGroup eventExecutors = new NioEventLoopGroup(1);
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(eventExecutors);
    }


    public static void multiply() {
        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(eventExecutors);
    }


    public static void workBoss() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup);
    }
}
