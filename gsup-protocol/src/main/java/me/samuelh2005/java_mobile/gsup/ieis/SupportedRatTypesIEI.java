package me.samuelh2005.java_mobile.gsup.ieis;

public record SupportedRatTypesIEI(int ratTypes) {
    public static SupportedRatTypesIEI decode(byte[] data) {
        if (data == null || data.length < 1) {
            return new SupportedRatTypesIEI(0);
        }
        return new SupportedRatTypesIEI(data[0] & 0xFF);
    }

    public static byte[] encode(SupportedRatTypesIEI iei) {
        return new byte[] {(byte) iei.ratTypes()};
    }

    public String toString() {
        return "SupportedRatTypesIEI{ratTypes=" + ratTypes + "}";
    }
}