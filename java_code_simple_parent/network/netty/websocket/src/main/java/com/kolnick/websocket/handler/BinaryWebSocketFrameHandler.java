package com.kolnick.websocket.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

public class BinaryWebSocketFrameHandler extends SimpleChannelInboundHandler<Object> {
    private WebSocketServerHandshaker handshaker;
    private static final String WEB_SOCKET_URL = "ws://localhost:8888/websocket";

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        //处理客户端向服务端发起http握手请求业务
        if (msg instanceof FullHttpRequest) {
            handHttpRequest(ctx, (FullHttpRequest) msg);
        } else if (msg instanceof WebSocketFrame) {
            //处理webSocket连接业务
            handWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }

    /**
     * 客户端与服务端创建连接时调用
     * Created by ww on 2019/2/16 22:44
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // NettyConfig.group.add(ctx.channel());
        System.out.println("客户端与服务端开始连接···");
    }

    /**
     * 客户端与服务端断开连接时调用
     * Created by ww on 2019/2/16 22:46
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // NettyConfig.group.remove(ctx.channel());
        System.out.println("客户端与服务端关闭连接···");
    }

    /**
     * 服务端接受客户端发送过来的数据结束之后时调用
     * Created by ww on 2019/2/16 22:44
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /**
     * 项目工程出现异常时调用
     * Created by ww on 2019/2/16 22:45
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 处理客户端向服务端发起http请求业务
     * Created by ww on 2019/2/16 23:01
     *
     * @param ctx
     * @param req
     * @return
     */
    private void handHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
        if (!req.getDecoderResult().isSuccess() || !("websocket".equals(req.headers().get("Upgrade")))) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(WEB_SOCKET_URL, null, false);
        handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.channel());
        } else {
            //    ChannelFuture handshake = handshaker.handshake(ctx.channel(), req);
            //       handshake.channel().pipeline().addLast(new MessageDecoder());
            //      handshake.channel().pipeline().addLast(new MessageEncoder());
            //     handshake.channel().pipeline().addLast(new ServerLogicHandler());

        }
    }

    /**
     * 服务端向客户端响应消息
     * Created by ww on 2019/2/16 23:04
     *
     * @param ctx
     * @param req
     * @param res
     * @return
     */
    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
        if (res.getStatus().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        //服务端向客户端发送数据
        ChannelFuture future = ctx.channel().writeAndFlush(res);
        if (res.getStatus().code() != 200) {
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }

    /**
     * 处理客户端与服务端之前的webSocket业务
     * Created by ww on 2019/2/16 23:28
     */
    private void handWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        //判断是否是关闭webSocket的指令
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
        } else if (frame instanceof PongWebSocketFrame) {
            //判断是否是ping指令
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        //判断是否是二进制指令
        if (!(frame instanceof TextWebSocketFrame)) {
            System.out.println("目前不支持二进制消息");
            throw new RuntimeException(" [ " + this.getClass().getName() + " ] 不支持消息 ");
        }
        //获取返回应答消息

    }

}
