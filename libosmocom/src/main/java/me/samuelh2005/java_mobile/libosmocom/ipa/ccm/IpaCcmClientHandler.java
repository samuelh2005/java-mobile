package me.samuelh2005.java_mobile.libosmocom.ipa.ccm;

import java.util.List;
import java.util.function.Consumer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import me.samuelh2005.java_mobile.libosmocom.ipa.IpaFrame;

public final class IpaCcmClientHandler extends SimpleChannelInboundHandler<IpaFrame> {
    private final IpaCcmIdentity identity;
    private final Consumer<Throwable> handshakeCallback;

    public IpaCcmClientHandler(IpaCcmIdentity identity, Consumer<Throwable> handshakeCallback) {
        this.identity = identity;
        this.handshakeCallback = handshakeCallback;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, IpaFrame frame) {
        if (!IpaCcmCodec.isCcmFrame(frame)) {
            ctx.fireChannelRead(frame);
            return;
        }

        int messageType = frame.proto() & 0xFF;
        switch (messageType) {
            case IpaCcmCodec.MSGT_ID_GET -> handleIdGet(ctx, frame);
            case IpaCcmCodec.MSGT_PING -> ctx.writeAndFlush(IpaCcmCodec.buildPong(ctx.alloc()));
            default -> {
                // Ignore other CCM messages.
            }
        }
    }

    private void handleIdGet(ChannelHandlerContext ctx, IpaFrame frame) {
        List<Integer> requestedTags = IpaCcmCodec.requestedTags(frame.payload());
        ctx.writeAndFlush(IpaCcmCodec.buildIdResp(ctx.alloc(), identity, requestedTags)).addListener(f -> {
            if (handshakeCallback == null) {
                return;
            }
            if (f.isSuccess()) {
                handshakeCallback.accept(null);
            } else {
                handshakeCallback.accept(f.cause());
            }
        });
    }
}
