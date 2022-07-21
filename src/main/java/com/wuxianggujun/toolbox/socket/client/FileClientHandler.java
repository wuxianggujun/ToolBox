package com.wuxianggujun.toolbox.socket.client;

import com.wuxianggujun.toolbox.socket.util.FileUtils;
import io.netty.channel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileClientHandler extends ChannelInboundHandlerAdapter {


    private static final Logger logger = LoggerFactory.getLogger(FileClientHandler.class);


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(FileUtils.readFile("C:\\Users\\MI\\IdeaProjects\\ToolBox\\LOG\\app_error.log"));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        logger.info("文件传输完成~");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel channel = ctx.channel();
        if (channel.isActive()) ctx.close();
    }
}
