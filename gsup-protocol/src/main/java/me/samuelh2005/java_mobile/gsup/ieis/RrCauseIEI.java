package me.samuelh2005.java_mobile.gsup.ieis;

public record RrCauseIEI(int cause) {
    public static RrCauseIEI decode(byte[] data) {
        return new RrCauseIEI(data != null && data.length > 0 ? data[0] & 0xFF : 0);
    }

    public static RrCauseIEI encode(int cause) {
        return new RrCauseIEI(cause);
    }

    public byte[] toBytes() {
        return new byte[] {(byte) cause};
    }

    public String toString() {
        return "RrCauseIEI{cause=" + cause + "}";
    }
}