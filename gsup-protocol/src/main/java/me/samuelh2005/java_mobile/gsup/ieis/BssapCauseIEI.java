package me.samuelh2005.java_mobile.gsup.ieis;

public record BssapCauseIEI(int cause) {
    public static BssapCauseIEI decode(byte[] data) {
        return new BssapCauseIEI(data != null && data.length > 0 ? data[0] & 0xFF : 0);
    }

    public static byte[] encode(BssapCauseIEI iei) {
        return new byte[] {(byte) iei.cause()};
    }

    public String toString() {
        return "BssapCauseIEI{cause=" + cause + "}";
    }
}