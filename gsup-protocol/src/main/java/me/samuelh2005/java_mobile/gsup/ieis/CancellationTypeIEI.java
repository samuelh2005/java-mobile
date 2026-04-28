package me.samuelh2005.java_mobile.gsup.ieis;

public record CancellationTypeIEI(int cancelType) {
    public static CancellationTypeIEI decode(byte[] data) {
        return new CancellationTypeIEI(data != null && data.length > 0 ? data[0] & 0xFF : 0);
    }

    public static byte[] encode(CancellationTypeIEI iei) {
        return new byte[] {(byte) iei.cancelType()};
    }

    public String toString() {
        return "CancellationTypeIEI{type=" + cancelType + "}";
    }
}