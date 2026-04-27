package me.samuelh2005.java_mobile.gsup.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import me.samuelh2005.java_mobile.gsup.IpaFrame;

public final class IpaFrameEncoder extends MessageToByteEncoder<IpaFrame> {
    @Override
    protected void encode(ChannelHandlerContext ctx, IpaFrame msg, ByteBuf out) {
        int len = msg.payload().readableBytes();
        out.writeShort(len);
        out.writeByte(msg.streamId());
        out.writeBytes(msg.payload(), msg.payload().readerIndex(), len);
    }
}