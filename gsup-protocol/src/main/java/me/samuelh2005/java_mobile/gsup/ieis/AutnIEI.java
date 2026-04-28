package me.samuelh2005.java_mobile.gsup.ieis;

public record AutnIEI(byte[] data) {
    public AutnIEI {
        if (data == null || data.length != 16) {
            throw new IllegalArgumentException("AUTN must be 16 bytes");
        }
        data = data.clone();
    }

    public static AutnIEI decode(byte[] data) {
        return new AutnIEI(data);
    }

    public static byte[] encode(AutnIEI iei) {
        return iei.data().clone();
    }

    public String toString() {
        return "AutnIEI{len=" + data.length + "}";
    }
}