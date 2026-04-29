package me.samuelh2005.java_mobile.libosmocom.gsup.ieis;

public record SmRpMrIEI(int messageReference) {
    public static SmRpMrIEI decode(byte[] data) {
        return new SmRpMrIEI(data != null && data.length > 0 ? data[0] & 0xFF : 0);
    }

    public static byte[] encode(SmRpMrIEI iei) {
        return new byte[] {(byte) iei.messageReference()};
    }

    public String toString() {
        return "SmRpMrIEI{mr=" + messageReference + "}";
    }
}