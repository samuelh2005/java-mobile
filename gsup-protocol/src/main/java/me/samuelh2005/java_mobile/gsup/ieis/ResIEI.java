package me.samuelh2005.java_mobile.gsup.ieis;

public record ResIEI(int type, byte[] value) implements IEI {
    public ResIEI {
        if (value == null) {
            throw new IllegalArgumentException("RES cannot be null");
        }
        value = value.clone();
    }

    @Override
    public byte[] value() {
        return value.clone();
    }

    @Override
    public String toString() {
        return "ResIEI{len=" + value.length + "}";
    }
}