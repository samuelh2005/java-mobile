package me.samuelh2005.java_mobile.gsup.codec;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import me.samuelh2005.java_mobile.gsup.IpaFrame;

public final class IpaFrameDecoder extends ByteToMessageDecoder {
    private static final int HEADER_LEN = 3;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        for (;;) {
            if (in.readableBytes() < HEADER_LEN) {
                return;
            }

            in.markReaderIndex();
            int payloadLen = in.readUnsignedShort(); // big-endian
            int streamId = in.readUnsignedByte();

            if (in.readableBytes() < payloadLen) {
                in.resetReaderIndex();
                return;
            }

            ByteBuf payload = in.readRetainedSlice(payloadLen);
            out.add(new IpaFrame(streamId, payload));
        }
    }
}