package me.samuelh2005.java_mobile.gsup.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.EncoderException;
import java.util.List;
import me.samuelh2005.java_mobile.gsup.GsupMessage;
import me.samuelh2005.java_mobile.gsup.IpaFrame;
import me.samuelh2005.java_mobile.gsup.ieis.IEI;

public final class GsupMessageEncoder extends MessageToMessageEncoder<GsupMessage> {

    private final int streamId;

    public GsupMessageEncoder() {
        this(0xEE); // Osmocom default for GSUP over IPA
    }

    public GsupMessageEncoder(int streamId) {
        this.streamId = streamId & 0xFF;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, GsupMessage msg, List<Object> out) {
        IEI[] ieis = msg.ieis() == null ? new IEI[0] : msg.ieis();

        int size = 1; // message type
        for (IEI iei : ieis) {
            int len = iei.value().length;
            if (len > 255) {
                throw new EncoderException("GSUP IE too long (max 255): type=0x"
                        + Integer.toHexString(iei.type() & 0xFF));
            }
            size += 2 + len; // tag + len + value
        }

        ByteBuf payload = ctx.alloc().buffer(size);
        payload.writeByte(msg.messageType() & 0xFF);

        for (IEI iei : ieis) {
            byte[] value = iei.value();
            payload.writeByte(iei.type() & 0xFF);
            payload.writeByte(value.length);
            payload.writeBytes(value);
        }

        out.add(new IpaFrame(streamId, payload));
    }
}
