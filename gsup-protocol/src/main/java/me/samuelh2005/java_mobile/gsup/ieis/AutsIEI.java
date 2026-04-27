package me.samuelh2005.java_mobile.gsup.ieis;

public record AutsIEI(int type, byte[] value) implements IEI {
    public AutsIEI {
        if (value == null || value.length != 14) {
            throw new IllegalArgumentException("AUTS must be 14 bytes");
        }
        value = value.clone();
    }

    @Override
    public byte[] value() {
        return value.clone();
    }

    @Override
    public String toString() {
        return "AutsIEI{len=" + value.length + "}";
    }
}