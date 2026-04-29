package me.samuelh2005.java_mobile.libosmocom.ipa.ccm;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.buffer.Unpooled;

class IpaCcmCodecTest {
    @Test
    void parsesRequestedTags() {
        List<Integer> tags = IpaCcmCodec.requestedTags(Unpooled.wrappedBuffer(new byte[] {
                0x01, 0x08,
                0x01, 0x07,
                0x01, 0x00
        }));

        assertEquals(List.of(0x08, 0x07, 0x00), tags);
    }

    @Test
    void encodesZeroTerminatedIdentityResponse() {
        IpaCcmIdentity identity = new IpaCcmIdentity("00112233", "unit-a", "", "", "", "", "0/0/0", "00:00:00:00:00:00");
        ByteBuf payload = IpaCcmCodec.buildIdResp(UnpooledByteBufAllocator.DEFAULT, identity, List.of(0x00, 0x08)).payload();
        byte[] bytes = new byte[payload.readableBytes()];
        payload.getBytes(payload.readerIndex(), bytes);

        assertTrue(bytes.length > 0);
        assertArrayEquals(new byte[] {
                0x00, 0x0A, 0x00, '0', '0', '1', '1', '2', '2', '3', '3', 0x00,
                0x00, 0x07, 0x08, '0', '/', '0', '/', '0', 0x00
        }, bytes);
    }
}
