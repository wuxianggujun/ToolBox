package com.wuxianggujun.toolbox.socket.server;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(FileServerHandler.class);

    /**
     * 当客户端主动连接服务器后，建立了通信通道并且可以传输数据
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel) ctx.channel();
        logger.info("连接主机ip地址 " + channel.remoteAddress().getAddress());
        ctx.writeAndFlush("HELLO: Type the path of the file to retrieve");
    }

    /**
     * 客户端和服务器断开了连接
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("客户端断开链接" + ctx.channel().localAddress().toString() + ";channelId:" + ctx.channel().hashCode());
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("接受的数据：" + msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        if (ctx.channel().isActive()) {
            ctx.writeAndFlush("Error: " + cause.getClass().getSimpleName() + ": " + cause.getMessage() + "\n").addListener(ChannelFutureListener.CLOSE);
        }
        ReferenceCountUtil.release(ctx);
    }
}
