package me.samuelh2005.java_mobile.gsup.ieis;

public record CancellationTypeIEI(int cancelType) {
    public static CancellationTypeIEI decode(byte[] data) {
        return new CancellationTypeIEI(data != null && data.length > 0 ? data[0] & 0xFF : 0);
    }

    public static CancellationTypeIEI encode(int cancelType) {
        return new CancellationTypeIEI(cancelType);
    }

    public byte[] toBytes() {
        return new byte[] {(byte) cancelType};
    }

    public String toString() {
        return "CancellationTypeIEI{type=" + cancelType + "}";
    }
}