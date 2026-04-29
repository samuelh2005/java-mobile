package me.samuelh2005.java_mobile.libosmocom.app;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import me.samuelh2005.java_mobile.libosmocom.gsup.GsupMessageDecoder;
import me.samuelh2005.java_mobile.libosmocom.gsup.GsupMessageEncoder;
import me.samuelh2005.java_mobile.libosmocom.ipa.IpaFrameDecoder;
import me.samuelh2005.java_mobile.libosmocom.ipa.IpaFrameEncoder;
import me.samuelh2005.java_mobile.libosmocom.ipa.ccm.IpaCcmClientHandler;
import me.samuelh2005.java_mobile.libosmocom.ipa.ccm.IpaCcmIdentity;

public class GsupClient {
    private final EventLoopGroup ioGroup = new NioEventLoopGroup(1); // one channel, one session
    private final Bootstrap bootstrap = new Bootstrap();
    private final Queue<GsupMessage> pendingMessages = new ConcurrentLinkedQueue<>();

    private volatile Channel channel;
    private volatile boolean ccmReady;

    public GsupClient(GsupHandler handler) {
        this(handler, IpaCcmIdentity.defaultIdentity());
    }

    public GsupClient(GsupHandler handler, IpaCcmIdentity identity) {
        bootstrap.group(ioGroup)
                 .channel(NioSocketChannel.class)
                 .option(ChannelOption.TCP_NODELAY, true)
                 .option(ChannelOption.SO_KEEPALIVE, true)
                 .handler(new ChannelInitializer<SocketChannel>() {
                     @Override
                      protected void initChannel(SocketChannel ch) {
                          ChannelPipeline p = ch.pipeline();
                          p.addLast(new IpaFrameDecoder());
                          p.addLast(new IpaFrameEncoder());
                          p.addLast(new ChannelInboundHandlerAdapter() {
                              @Override
                              public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                                  ccmReady = false;
                                  super.channelInactive(ctx);
                              }
                          });
                          p.addLast(new IpaCcmClientHandler(identity, GsupClient.this::markCcmReady));
                          p.addLast(new GsupMessageDecoder());
                          p.addLast(new GsupMessageEncoder());
                          p.addLast(new GsupMessageHandlerAdapter(handler));
                      }
                  });
    }

    public ChannelFuture connect(String host, int port) {
        return bootstrap.connect(host, port).addListener((ChannelFuture f) -> {
            if (f.isSuccess()) {
                channel = f.channel();
            } else {
                scheduleReconnect(host, port);
            }
        });
    }

    private void scheduleReconnect(String host, int port) {
        // EventLoop is a ScheduledExecutorService, so schedule reconnect there.
        ioGroup.next().schedule(() -> connect(host, port), 5, TimeUnit.SECONDS);
    }

    public void send(GsupMessage msg) {
        Channel ch = channel;
        if (ch != null && ch.isActive() && ccmReady) {
            ch.writeAndFlush(msg);
            return;
        }

        pendingMessages.add(msg);
    }

    public void shutdown() {
        ioGroup.shutdownGracefully();
    }

    private void markCcmReady() {
        ccmReady = true;
        flushPendingMessages();
    }

    private void flushPendingMessages() {
        Channel ch = channel;
        if (ch == null || !ch.isActive() || !ccmReady) {
            return;
        }

        GsupMessage msg;
        while ((msg = pendingMessages.poll()) != null) {
            ch.writeAndFlush(msg);
        }
    }
}
