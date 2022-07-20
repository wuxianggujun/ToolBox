package com.wuxianggujun.toolbox.socket.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.CharsetUtil;

public class FileServerInitializer extends ChannelInitializer<SocketChannel> {
    private final SslContext sslCtx;

    public FileServerInitializer(SslContext sslCtx) {
        this.sslCtx = sslCtx;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline p = socketChannel.pipeline();
        if (sslCtx != null) {
            p.addLast(sslCtx.newHandler(socketChannel.alloc()));
        }
        p.addLast(
                new StringEncoder(CharsetUtil.UTF_8),
                new LineBasedFrameDecoder(8192),
                new StringDecoder(CharsetUtil.UTF_8),
                new ChunkedWriteHandler(),
                new FileServerHandler());
    }
}
