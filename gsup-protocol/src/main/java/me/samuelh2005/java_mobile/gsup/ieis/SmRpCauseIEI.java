package me.samuelh2005.java_mobile.gsup.ieis;

public record SmRpCauseIEI(int cause) {
    public static SmRpCauseIEI decode(byte[] data) {
        return new SmRpCauseIEI(data != null && data.length > 0 ? data[0] & 0xFF : 0);
    }

    public static SmRpCauseIEI encode(int cause) {
        return new SmRpCauseIEI(cause);
    }

    public byte[] toBytes() {
        return new byte[] {(byte) cause};
    }

    public String toString() {
        return "SmRpCauseIEI{cause=" + cause + "}";
    }
}