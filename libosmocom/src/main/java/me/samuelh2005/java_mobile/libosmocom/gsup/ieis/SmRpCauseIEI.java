package me.samuelh2005.java_mobile.libosmocom.gsup.ieis;

public record SmRpCauseIEI(int cause) {
    public static SmRpCauseIEI decode(byte[] data) {
        return new SmRpCauseIEI(data != null && data.length > 0 ? data[0] & 0xFF : 0);
    }

    public static byte[] encode(SmRpCauseIEI iei) {
        return new byte[] {(byte) iei.cause()};
    }

    public String toString() {
        return "SmRpCauseIEI{cause=" + cause + "}";
    }
}