package com.wuxianggujun.toolbox.socket.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileServer {

    private static String bindHost = "127.0.0.1";
    private static int bindPort = 8080;

    private static final Logger logger = LoggerFactory.getLogger(FileServer.class);


    public static void main(String[] args) {

        //Configure the server
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)//服务器接收连接的队列长度，如果队列已满，客户端连接将被拒绝.
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.ALLOW_HALF_CLOSURE, true)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .localAddress(bindHost, bindPort)//设置监听端口
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new FileServerInitializer());

            //Start the server
            ChannelFuture f = b.bind().sync();
            logger.info("文件服务器启动成功！");

            f.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            logger.error("Start netty with port:" + bindPort + " failed.", e);
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
