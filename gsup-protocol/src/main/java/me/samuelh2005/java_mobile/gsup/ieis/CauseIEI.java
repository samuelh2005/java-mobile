package me.samuelh2005.java_mobile.gsup.ieis;

public record CauseIEI(int cause) {

    public static CauseIEI decode(byte[] data) {
        return new CauseIEI(data != null && data.length > 0 ? data[0] & 0xFF : 0);
    }

    public static byte[] encode(CauseIEI iei) {
        return new byte[] {(byte) iei.cause()};
    }

    public String toString() {
        return "CauseIEI{cause=" + cause + "}";
    }
}