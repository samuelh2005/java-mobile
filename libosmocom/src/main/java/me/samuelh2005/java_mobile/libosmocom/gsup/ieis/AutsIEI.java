package me.samuelh2005.java_mobile.libosmocom.gsup.ieis;

public record AutsIEI(byte[] data) {
    public AutsIEI {
        if (data == null || data.length != 14) {
            throw new IllegalArgumentException("AUTS must be 14 bytes");
        }
        data = data.clone();
    }

    public static AutsIEI decode(byte[] data) {
        return new AutsIEI(data);
    }

    public static byte[] encode(AutsIEI iei) {
        return iei.data().clone();
    }

    public String toString() {
        return "AutsIEI{len=" + data.length + "}";
    }
}