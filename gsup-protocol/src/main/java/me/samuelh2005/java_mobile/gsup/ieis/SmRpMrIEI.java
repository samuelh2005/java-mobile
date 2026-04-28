package me.samuelh2005.java_mobile.gsup.ieis;

public record SmRpMrIEI(int messageReference) {
    public static SmRpMrIEI decode(byte[] data) {
        return new SmRpMrIEI(data != null && data.length > 0 ? data[0] & 0xFF : 0);
    }

    public static SmRpMrIEI encode(int messageReference) {
        return new SmRpMrIEI(messageReference);
    }

    public byte[] toBytes() {
        return new byte[] {(byte) messageReference};
    }

    public String toString() {
        return "SmRpMrIEI{mr=" + messageReference + "}";
    }
}