package me.samuelh2005.java_mobile.libosmocom.gsup.ieis;

import me.samuelh2005.java_mobile.libosmocom.primitive.BcdUtil;

public record ImeiIEI(String imei) {
    public ImeiIEI {
        imei = imei == null ? "" : imei;
    }

    public static ImeiIEI decode(byte[] data) {
        return new ImeiIEI(BcdUtil.decodeDigits(data, 0));
    }

    public static byte[] encode(ImeiIEI iei) {
        return BcdUtil.encodeDigits(iei.imei());
    }

    public String toString() {
        return "ImeiIEI{imei=" + imei + "}";
    }
}
