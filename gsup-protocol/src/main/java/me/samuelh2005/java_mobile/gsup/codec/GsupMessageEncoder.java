package me.samuelh2005.java_mobile.gsup.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.EncoderException;
import java.util.List;
import me.samuelh2005.java_mobile.gsup.GsupMessage;
import me.samuelh2005.java_mobile.gsup.IpaFrame;
import me.samuelh2005.java_mobile.gsup.ieis.IEIType;

public final class GsupMessageEncoder extends MessageToMessageEncoder<GsupMessage> {

    private final int streamId;

    public GsupMessageEncoder() {
        this(0xEE);
    }

    public GsupMessageEncoder(int streamId) {
        this.streamId = streamId & 0xFF;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, GsupMessage msg, List<Object> out) {
        Object[] ieis = msg.ieis() == null ? new Object[0] : msg.ieis();
        int[] codes = msg.codes();

        int size = 1;
        for (Object iei : ieis) {
            byte[] value = IEIType.encode(codes[0], iei);
            int len = value.length;
            if (len > 255) {
                throw new EncoderException("GSUP IE too long (max 255)");
            }
            size += 2 + len;
        }

        ByteBuf payload = ctx.alloc().buffer(size);
        payload.writeByte(msg.messageType() & 0xFF);

        for (int i = 0; i < ieis.length; i++) {
            byte[] value = IEIType.encode(codes[i], ieis[i]);
            payload.writeByte(codes[i] & 0xFF);
            payload.writeByte(value.length);
            payload.writeBytes(value);
        }

        out.add(new IpaFrame(streamId, payload));
    }
}