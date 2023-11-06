package com.kolnick.demo.client;

import com.kolnick.demo.Worker;
import com.kolnick.demo.client.handler.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author caochaojie
 * 2018/11/15 9:47
 * @description
 */
public class FirstNettyClient {

    private volatile boolean start;

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }



    public void start() {
        EventLoopGroup workGroup = new NioEventLoopGroup();

        Bootstrap b = new Bootstrap();
        b.group(workGroup);
        b.channel(NioSocketChannel.class);
        b.option(ChannelOption.TCP_NODELAY, true);
        b.handler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel sc) throws Exception {
                sc.pipeline().addLast(new LineBasedFrameDecoder(100));
                sc.pipeline().addLast(new StringDecoder());
                sc.pipeline().addLast(new ClientHandler());
            }

        });
        Worker worker = Worker.getInstance();
        ChannelFuture future = null;
        try {
            future = b.connect("127.0.0.1", 8888).sync();
            if (future.isSuccess()) {
                System.out.println("链接服务器成功");
                this.start = true;
            }
            Lock shutDownLock = worker.getShutDownLock();
            try {
                shutDownLock.lock();
                Condition shutDownCondition = worker.getShutDownCondition();
                shutDownCondition.await();
            } finally {
                shutDownLock.unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        workGroup.shutdownGracefully();
    }


}
