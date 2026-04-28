package me.samuelh2005.java_mobile.gsup.ieis;

public record UnknownIEI(int type, byte[] value) {
    public UnknownIEI {
        value = value == null ? new byte[0] : value.clone();
    }

    public static UnknownIEI decode(int type, byte[] value) {
        return new UnknownIEI(type, value);
    }

    public byte[] encode() {
        return value.clone();
    }

    public String toString() {
        return "UnknownIEI{type=0x%02X, len=%d}".formatted(type & 0xFF, value.length);
    }
}