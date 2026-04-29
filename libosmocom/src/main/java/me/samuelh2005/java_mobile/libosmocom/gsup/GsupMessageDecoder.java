package me.samuelh2005.java_mobile.libosmocom.gsup;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.CorruptedFrameException;
import io.netty.handler.codec.MessageToMessageDecoder;
import me.samuelh2005.java_mobile.libosmocom.app.GsupMessage;
import me.samuelh2005.java_mobile.libosmocom.gsup.ieis.IEIType;
import me.samuelh2005.java_mobile.libosmocom.ipa.IpaFrame;

import java.util.ArrayList;
import java.util.List;

public final class GsupMessageDecoder extends MessageToMessageDecoder<IpaFrame> {

    @Override
    protected void decode(ChannelHandlerContext ctx, IpaFrame frame, List<Object> out) {
        ByteBuf in = frame.payload();
        if (!in.isReadable()) {
            return;
        }

        int messageType = in.readUnsignedByte();
        List<Object> ieis = new ArrayList<>();

        while (in.isReadable()) {
            if (in.readableBytes() < 2) {
                throw new CorruptedFrameException("truncated GSUP IE header");
            }

            int code = in.readUnsignedByte();
            int len = in.readUnsignedByte();

            if (in.readableBytes() < len) {
                throw new CorruptedFrameException("truncated GSUP IE value");
            }

            byte[] value = new byte[len];
            in.readBytes(value);
            ieis.add(IEIType.decode(code, value));
        }

        out.add(new GsupMessage(messageType, ieis.toArray()));
    }
}