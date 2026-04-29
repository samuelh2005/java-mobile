package me.samuelh2005.java_mobile.libosmocom.ipa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;

class IpaFrameDecoderTest {
    @Test
    void decodesCcmIdGetBodyWithoutTheMessageTypeByte() {
        EmbeddedChannel ch = new EmbeddedChannel(new IpaFrameDecoder());

        ch.writeInbound(Unpooled.wrappedBuffer(new byte[] {
                0x00, 0x11,
                (byte) 0xFE,
                0x04,
                0x01, 0x08,
                0x01, 0x07,
                0x01, 0x02,
                0x01, 0x03,
                0x01, 0x04,
                0x01, 0x05,
                0x01, 0x01,
                0x01, 0x00
        }));

        IpaFrame frame = ch.readInbound();

        assertNotNull(frame);
        assertEquals(0xFE, frame.streamId());
        assertEquals(0x04, frame.proto());
        byte[] body = new byte[frame.payload().readableBytes()];
        frame.payload().getBytes(frame.payload().readerIndex(), body);
        assertArrayEquals(new byte[] {
                0x01, 0x08,
                0x01, 0x07,
                0x01, 0x02,
                0x01, 0x03,
                0x01, 0x04,
                0x01, 0x05,
                0x01, 0x01,
                0x01, 0x00
        }, body);
    }
}
