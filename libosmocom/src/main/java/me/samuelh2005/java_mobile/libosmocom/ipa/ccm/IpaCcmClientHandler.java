package me.samuelh2005.java_mobile.libosmocom.ipa.ccm;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import me.samuelh2005.java_mobile.libosmocom.ipa.IpaFrame;

public final class IpaCcmClientHandler extends SimpleChannelInboundHandler<IpaFrame> {
    private final IpaCcmIdentity identity;
    private final Runnable readyCallback;
    private final AtomicBoolean readySignalled = new AtomicBoolean(false);

    public IpaCcmClientHandler(IpaCcmIdentity identity, Runnable readyCallback) {
        this.identity = identity;
        this.readyCallback = readyCallback;
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
            case IpaCcmCodec.MSGT_ID_ACK -> signalReady();
            default -> {
                // Ignore other CCM messages.
            }
        }
    }

    private void handleIdGet(ChannelHandlerContext ctx, IpaFrame frame) {
        List<Integer> requestedTags = IpaCcmCodec.requestedTags(frame.payload());
        ctx.writeAndFlush(IpaCcmCodec.buildIdResp(ctx.alloc(), identity, requestedTags));
        signalReady();
    }

    private void signalReady() {
        if (readySignalled.compareAndSet(false, true) && readyCallback != null) {
            readyCallback.run();
        }
    }
}
