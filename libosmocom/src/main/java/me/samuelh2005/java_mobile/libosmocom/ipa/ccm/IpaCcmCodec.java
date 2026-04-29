package me.samuelh2005.java_mobile.libosmocom.ipa.ccm;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import me.samuelh2005.java_mobile.libosmocom.ipa.IpaFrame;

public final class IpaCcmCodec {
    public static final int STREAM_ID = 0xFE;
    public static final int MSGT_PING = 0x00;
    public static final int MSGT_PONG = 0x01;
    public static final int MSGT_ID_GET = 0x04;
    public static final int MSGT_ID_RESP = 0x05;
    public static final int MSGT_ID_ACK = 0x06;
    public static final int MSGT_ID_NACK = 0x07;

    private IpaCcmCodec() {}

    public static boolean isCcmFrame(IpaFrame frame) {
        return frame != null && (frame.streamId() & 0xFF) == STREAM_ID;
    }

    public static List<Integer> requestedTags(ByteBuf payload) {
        ByteBuf in = payload.duplicate();
        List<Integer> tags = new ArrayList<>();

        while (in.isReadable()) {
            if (in.readableBytes() < 2) {
                throw new IllegalArgumentException("truncated IPA CCM ID_GET");
            }

            int len = in.readUnsignedByte();
            int tag = in.readUnsignedByte();

            if (len == 0) {
                throw new IllegalArgumentException("invalid IPA CCM tag length");
            }

            int valueLen = len - 1;
            if (in.readableBytes() < valueLen) {
                throw new IllegalArgumentException("truncated IPA CCM ID_GET value");
            }

            in.skipBytes(valueLen);
            tags.add(tag);
        }

        return tags;
    }

    public static IpaFrame buildPong(ByteBufAllocator alloc) {
        return new IpaFrame(STREAM_ID, MSGT_PONG, alloc.buffer(0, 0));
    }

    public static IpaFrame buildIdResp(ByteBufAllocator alloc, IpaCcmIdentity identity, List<Integer> requestedTags) {
        ByteBuf payload = alloc.buffer();

        for (int requestedTag : requestedTags) {
            IpaCcmIdTag tag = IpaCcmIdTag.fromCode(requestedTag);
            if (tag == null) {
                continue;
            }

            String value = identity.valueFor(tag);
            byte[] bytes = value.getBytes(StandardCharsets.US_ASCII);
            payload.writeShort(bytes.length + 2);
            payload.writeByte(tag.code());
            payload.writeBytes(bytes);
            payload.writeByte(0x00);
        }

        return new IpaFrame(STREAM_ID, MSGT_ID_RESP, payload);
    }
}
