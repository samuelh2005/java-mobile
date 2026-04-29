package me.samuelh2005.java_mobile.libosmocom.gsup.ieis;

public record RrCauseIEI(int cause) {
    public static RrCauseIEI decode(byte[] data) {
        return new RrCauseIEI(data != null && data.length > 0 ? data[0] & 0xFF : 0);
    }

    public static byte[] encode(RrCauseIEI iei) {
        return new byte[] {(byte) iei.cause()};
    }

    public String toString() {
        return "RrCauseIEI{cause=" + cause + "}";
    }
}