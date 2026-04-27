package me.samuelh2005.java_mobile.gsup.ieis;

public record SresIEI(int type, byte[] value) implements IEI {
    public SresIEI {
        if (value == null || value.length != 4) {
            throw new IllegalArgumentException("SRES must be 4 bytes");
        }
        value = value.clone();
    }

    @Override
    public byte[] value() {
        return value.clone();
    }

    @Override
    public String toString() {
        return "SresIEI{len=" + value.length + "}";
    }
}