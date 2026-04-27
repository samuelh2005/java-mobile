package me.samuelh2005.java_mobile.gsup;

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

public class GsupClient {
    private final EventLoopGroup ioGroup = new NioEventLoopGroup(1); // one channel, one session
    private final Bootstrap bootstrap = new Bootstrap();

    private volatile Channel channel;

    public GsupClient() {
        bootstrap.group(ioGroup)
                 .channel(NioSocketChannel.class)
                 .option(ChannelOption.TCP_NODELAY, true)
                 .option(ChannelOption.SO_KEEPALIVE, true)
                 .handler(new ChannelInitializer<SocketChannel>() {
                     @Override
                     protected void initChannel(SocketChannel ch) {
                         ChannelPipeline p = ch.pipeline();
                        //  p.addLast(new IpaFrameDecoder());
                        //  p.addLast(new IpaFrameEncoder());
                        //  p.addLast(new GsupMessageDecoder());
                        //  p.addLast(new GsupMessageEncoder());
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

    // public void send(GsupMessage msg) {
    //     Channel ch = channel;
    //     if (ch != null && ch.isActive()) {
    //         ch.writeAndFlush(msg);
    //     }
    // }

    public void shutdown() {
        ioGroup.shutdownGracefully();
    }
}
