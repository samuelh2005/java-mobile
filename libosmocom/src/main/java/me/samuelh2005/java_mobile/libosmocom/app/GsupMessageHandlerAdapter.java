package me.samuelh2005.java_mobile.libosmocom.app;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class GsupMessageHandlerAdapter extends ChannelInboundHandlerAdapter {
    
    private final GsupHandler handler;

    public GsupMessageHandlerAdapter(GsupHandler handler) {
        this.handler = handler;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        handler.channelActive(ctx);
        ctx.flush();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        handler.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof GsupMessage) {
            handler.messageReceived(ctx, (GsupMessage) msg);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        handler.exceptionCaught(ctx, cause);
    }
}