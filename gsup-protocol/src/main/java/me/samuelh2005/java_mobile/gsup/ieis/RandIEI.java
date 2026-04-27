package me.samuelh2005.java_mobile.gsup.ieis;

public record RandIEI(int type, byte[] value) implements IEI {
    public RandIEI {
        if (value == null || value.length != 16) {
            throw new IllegalArgumentException("RAND must be 16 bytes");
        }
        value = value.clone();
    }

    @Override
    public byte[] value() {
        return value.clone();
    }

    @Override
    public String toString() {
        return "RandIEI{len=" + value.length + "}";
    }
}