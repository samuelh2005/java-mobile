package me.samuelh2005.java_mobile.libosmocom.gsup.ieis;

import me.samuelh2005.java_mobile.libosmocom.primitive.BcdUtil;

public record MsisdnIEI(String value) {
    public MsisdnIEI {
        value = value == null ? "" : value;
    }

    public static MsisdnIEI decode(byte[] data) {
        return new MsisdnIEI(BcdUtil.decodeDigits(data, 1));
    }

    public static byte[] encode(MsisdnIEI iei) {
        return BcdUtil.encodeDigitsWithPrefix(0x91, iei.value);
    }

    public String toString() {
        return "MsisdnIEI{msisdn=" + value + "}";
    }
}
