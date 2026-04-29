package me.samuelh2005.java_mobile.libosmocom.gsup;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import me.samuelh2005.java_mobile.libosmocom.gsup.ieis.IEIType;
import me.samuelh2005.java_mobile.libosmocom.ipa.IpaFrame;
import io.netty.handler.codec.EncoderException;
import java.util.List;

public final class GsupMessageEncoder extends MessageToMessageEncoder<RawGsupMessage> {

    private final int streamId;
    private final int proto;

    public GsupMessageEncoder() {
        this(0xEE, 0x05);
    }

    public GsupMessageEncoder(int streamId, int proto) {
        this.streamId = streamId & 0xFF;
        this.proto = proto & 0xFF;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, RawGsupMessage msg, List<Object> out) {
        Object[] ieis = msg.ieis() == null ? new Object[0] : msg.ieis();

        int size = 1;
        for (Object iei : ieis) {
            byte[] value = IEIType.encode(iei);
            int len = value.length;
            if (len > 255) {
                throw new EncoderException("GSUP IE too long (max 255)");
            }
            size += 2 + len;
        }

        ByteBuf payload = ctx.alloc().buffer(size);
        payload.writeByte(msg.messageType() & 0xFF);

        for (Object iei : ieis) {
            int code = IEIType.codeOf(iei);
            byte[] value = IEIType.encode(iei);
            payload.writeByte(code);
            payload.writeByte(value.length);
            payload.writeBytes(value);

            System.out.println("Encoding IEI 0x" + Integer.toHexString(code) + " len=" + value.length + " data=" + bytesToHex(value));
        }

        out.add(new IpaFrame(streamId, proto, payload));
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString();
    }
}