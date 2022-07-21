package com.wuxianggujun.toolbox.socket.client;

import com.wuxianggujun.toolbox.socket.util.FileUtils;
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
    private static final String HOST = System.getProperty("host", "127.0.0.1");

    private static final int PORT = Integer.parseInt(System.getProperty("port", "8080"));
    private static EventLoopGroup workerGroup = new NioEventLoopGroup();
    private static Bootstrap bootstrap = new Bootstrap();
    private static ChannelFuture channelFuture;

    public static void main(String[] args) throws InterruptedException {

        try {
            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.AUTO_READ, true)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new FileClientInitializer());

            channelFuture = bootstrap.connect(HOST, PORT).sync();
            if (channelFuture != null && channelFuture.isSuccess()) {
                LOG.info("客户端连接成功！");
            } else {
                LOG.info("客户端连接失败！");
            }
         
//            channelFuture.channel().closeFuture().addListener(future -> {
//                LOG.info("我现在是CloseFuture：" + future);
//            });
            
            //程序会一直运行，直到channel关闭
            channelFuture.channel().closeFuture().sync();
           
           


        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("客户端连接失败: " + e.getMessage());
        } finally {
            workerGroup.shutdownGracefully().sync();
        }
    }


}
