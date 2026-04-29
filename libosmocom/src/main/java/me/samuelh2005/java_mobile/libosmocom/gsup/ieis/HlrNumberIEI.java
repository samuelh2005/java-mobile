package me.samuelh2005.java_mobile.libosmocom.gsup.ieis;

import me.samuelh2005.java_mobile.libosmocom.primitive.BcdUtil;

public record HlrNumberIEI(String number) {
    public HlrNumberIEI {
        number = number == null ? "" : number;
    }

    public static HlrNumberIEI decode(byte[] data) {
        return new HlrNumberIEI(BcdUtil.decodeDigits(data, 0));
    }

    public static byte[] encode(HlrNumberIEI iei) {
        return BcdUtil.encodeDigits(iei.number());
    }

    public String toString() {
        return "HlrNumberIEI{number=" + number + "}";
    }
}
