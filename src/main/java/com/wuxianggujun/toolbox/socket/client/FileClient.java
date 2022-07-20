package com.wuxianggujun.toolbox.socket.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileClient {
    private static final Logger LOG = LoggerFactory.getLogger(FileClient.class);
    private static String host = "127.0.0.1";
    private static int port = 8080;
    private static EventLoopGroup workerGroup = new NioEventLoopGroup();
    private static Bootstrap bootstrap = new Bootstrap();
    private static ChannelFuture channelFuture;

    public static void main(String[] args) {

        try {
            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.AUTO_READ, true)
                    .handler(new FileClientHandler());

            channelFuture = bootstrap.connect(host, port).sync();
            
            channelFuture.channel().closeFuture().addListener(future -> {
                LOG.info("我现在是CloseFuture：" + future);
            });
//            //程序会一直等待，知道channel关闭
//            ChannelFuture channelFuture1 = channelFuture.channel().closeFuture();
//            channelFuture1.sync();

        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("客户端连接失败: " + e.getMessage());
        } finally {
            if (channelFuture != null && channelFuture.isSuccess()) {
                LOG.info("客户端连接成功！");
            } else {
                LOG.info("客户端连接失败！");
            }
            workerGroup.shutdownGracefully();
        }
    }


}
