package me.samuelh2005.java_mobile.gsup.ieis;

public record UnknownIEI(int type, byte[] value) implements IEI {
    public UnknownIEI {
        value = value == null ? new byte[0] : value.clone();
    }

    @Override
    public byte[] value() {
        return value.clone();
    }

    @Override
    public String toString() {
        return "UnknownIEI{type=0x%02X, len=%d}".formatted(type & 0xFF, value.length);
    }
}