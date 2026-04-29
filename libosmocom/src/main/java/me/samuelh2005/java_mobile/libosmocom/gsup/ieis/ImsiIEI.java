package me.samuelh2005.java_mobile.libosmocom.gsup.ieis;

import me.samuelh2005.java_mobile.libosmocom.primitive.BcdUtil;

public record ImsiIEI(String value) {
    public ImsiIEI {
        value = value == null ? "" : value;
    }

    public static ImsiIEI decode(byte[] data) {
        return new ImsiIEI(BcdUtil.decodeDigits(data, 0));
    }

    public static byte[] encode(ImsiIEI iei) {
        return BcdUtil.encodeDigits(iei.value);
    }

    public String toString() {
        return "ImsiIEI{imsi=" + value + "}";
    }
}
