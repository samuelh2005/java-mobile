package me.samuelh2005.java_mobile.gsup.ieis;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BcdUtilTest {
    @Test
    void encodesImsiLikeOsmocom() {
        assertArrayEquals(
                new byte[] {(byte) 0x00, (byte) 0x01, (byte) 0x01, (byte) 0x21, (byte) 0x43, (byte) 0x65, (byte) 0x87, (byte) 0xF9},
                BcdUtil.encodeDigits("001010123456789"));
    }

    @Test
    void decodesImsiLikeOsmocom() {
        assertEquals("001010123456789",
                BcdUtil.decodeDigits(new byte[] {(byte) 0x00, (byte) 0x01, (byte) 0x01, (byte) 0x21, (byte) 0x43, (byte) 0x65, (byte) 0x87, (byte) 0xF9}, 0));
    }

    @Test
    void encodesMsisdnWithPrefixAndFillerNibble() {
        assertArrayEquals(
                new byte[] {(byte) 0x91, (byte) 0x44, (byte) 0x16, (byte) 0x32, (byte) 0x24, (byte) 0x34},
                BcdUtil.encodeDigitsWithPrefix(0x91, "4461234243"));
    }
}
