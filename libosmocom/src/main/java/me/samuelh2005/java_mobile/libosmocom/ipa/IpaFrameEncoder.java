package me.samuelh2005.java_mobile.libosmocom.ipa;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public final class IpaFrameEncoder extends MessageToByteEncoder<IpaFrame> {
    @Override
    protected void encode(ChannelHandlerContext ctx, IpaFrame msg, ByteBuf out) {
        int len = msg.payload().readableBytes();
        out.writeShort(len+1); // we need to plus one as the payload also contains the protocol byte
        out.writeByte(msg.streamId());
        out.writeByte(msg.proto());

        byte[] payloadBytes = new byte[len];
        msg.payload().getBytes(msg.payload().readerIndex(), payloadBytes);
        System.out.println("IPA Frame: len=" + len + " streamId=0x" + Integer.toHexString(msg.streamId()) + " proto=0x" + Integer.toHexString(msg.proto()) + " payload=" + bytesToHex(payloadBytes));

        out.writeBytes(msg.payload(), msg.payload().readerIndex(), len);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString();
    }
}