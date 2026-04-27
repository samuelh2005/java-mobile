package me.samuelh2005.java_mobile.gsup.ieis;

public record KcIEI(int type, byte[] value) implements IEI {
    public KcIEI {
        if (value == null || value.length != 8) {
            throw new IllegalArgumentException("Kc must be 8 bytes");
        }
        value = value.clone();
    }

    @Override
    public byte[] value() {
        return value.clone();
    }

    @Override
    public String toString() {
        return "KcIEI{len=" + value.length + "}";
    }
}