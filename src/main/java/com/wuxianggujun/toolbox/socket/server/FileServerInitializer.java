package com.wuxianggujun.toolbox.socket.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class FileServerInitializer extends ChannelInitializer<SocketChannel> {

    public FileServerInitializer() {

    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline p = socketChannel.pipeline();
        p.addLast(new StringEncoder(CharsetUtil.UTF_8));
        p.addLast(new LineBasedFrameDecoder(1024));
        p.addLast(new StringDecoder(CharsetUtil.UTF_8));
        p.addLast(new FileServerHandler());
    }
}
