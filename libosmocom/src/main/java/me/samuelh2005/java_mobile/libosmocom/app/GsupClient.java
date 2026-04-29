package me.samuelh2005.java_mobile.libosmocom.app;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
import me.samuelh2005.java_mobile.libosmocom.gsup.RawGsupMessage;
import me.samuelh2005.java_mobile.libosmocom.gsup.GsupMessageDecoder;
import me.samuelh2005.java_mobile.libosmocom.gsup.GsupMessageEncoder;
import me.samuelh2005.java_mobile.libosmocom.ipa.IpaFrameDecoder;
import me.samuelh2005.java_mobile.libosmocom.ipa.IpaFrameEncoder;
import me.samuelh2005.java_mobile.libosmocom.ipa.ccm.IpaCcmClientHandler;
import me.samuelh2005.java_mobile.libosmocom.ipa.ccm.IpaCcmIdentity;

public class GsupClient {
    private final EventLoopGroup ioGroup = new NioEventLoopGroup(1); // one channel, one session
    private final Bootstrap bootstrap = new Bootstrap();

    private volatile Channel channel;
    private volatile CompletableFuture<Void> handshakeFuture;

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
                                  channel = null;
                                  failHandshake(new IllegalStateException("Channel closed before CCM handshake completed"));
                                  super.channelInactive(ctx);
                              }
                          });
                          p.addLast(new IpaCcmClientHandler(identity, GsupClient.this::onHandshakeSignal));
                          p.addLast(new GsupMessageDecoder());
                          p.addLast(new GsupMessageEncoder());
                          p.addLast(new GsupMessageHandlerAdapter(handler));
                      }
                  });
    }

    public ChannelFuture connect(String host, int port) {
        CompletableFuture<Void> handshake = new CompletableFuture<>();
        handshakeFuture = handshake;

        ChannelFuture future = bootstrap.connect(host, port).addListener((ChannelFuture f) -> {
            if (f.isSuccess()) {
                channel = f.channel();
            } else {
                failHandshake(f.cause() != null ? f.cause() : new IllegalStateException("connect failed"));
                scheduleReconnect(host, port);
            }
        });

        future.awaitUninterruptibly();
        if (!future.isSuccess()) {
            handshakeFuture = null;
            return future;
        }

        try {
            handshake.get(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while waiting for CCM handshake", e);
        } catch (ExecutionException e) {
            throw new IllegalStateException("CCM handshake failed", e.getCause());
        } catch (TimeoutException e) {
            Channel ch = channel;
            if (ch != null) {
                ch.close().awaitUninterruptibly();
            }
            scheduleReconnect(host, port);
            throw new IllegalStateException("Timed out waiting for CCM handshake", e);
        } finally {
            if (handshakeFuture == handshake) {
                handshakeFuture = null;
            }
        }

        return future;
    }

    private void scheduleReconnect(String host, int port) {
        // EventLoop is a ScheduledExecutorService, so schedule reconnect there.
        ioGroup.next().schedule(() -> connect(host, port), 5, TimeUnit.SECONDS);
    }

    public void send(RawGsupMessage msg) {
        Channel ch = channel;
        if (ch == null || !ch.isActive()) {
            throw new IllegalStateException("GSUP channel is not connected");
        }
        ch.writeAndFlush(msg);
    }

    public void shutdown() {
        ioGroup.shutdownGracefully();
    }

    private void onHandshakeSignal(Throwable cause) {
        CompletableFuture<Void> handshake = handshakeFuture;
        if (handshake == null || handshake.isDone()) {
            return;
        }

        if (cause == null) {
            handshake.complete(null);
        } else {
            handshake.completeExceptionally(cause);
        }
    }

    private void failHandshake(Throwable cause) {
        onHandshakeSignal(cause);
    }
}
