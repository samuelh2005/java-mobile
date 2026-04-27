package me.samuelh2005.java_mobile.gsup.ieis;

public record AutnIEI(int type, byte[] value) implements IEI {
    public AutnIEI {
        if (value == null || value.length != 16) {
            throw new IllegalArgumentException("AUTN must be 16 bytes");
        }
        value = value.clone();
    }

    @Override
    public byte[] value() {
        return value.clone();
    }

    @Override
    public String toString() {
        return "AutnIEI{len=" + value.length + "}";
    }
}