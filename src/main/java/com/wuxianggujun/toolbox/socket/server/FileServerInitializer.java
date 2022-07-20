package com.wuxianggujun.toolbox.socket.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class FileServerInitializer extends ChannelInitializer<SocketChannel> {

    public FileServerInitializer() {

    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline p = socketChannel.pipeline();
        p.addLast(new FileServerHandler());
    }
}
