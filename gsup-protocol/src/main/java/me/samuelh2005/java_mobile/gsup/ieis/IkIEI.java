package me.samuelh2005.java_mobile.gsup.ieis;

public record IkIEI(int type, byte[] value) implements IEI {
    public IkIEI {
        if (value == null || value.length != 16) {
            throw new IllegalArgumentException("IK must be 16 bytes");
        }
        value = value.clone();
    }

    @Override
    public byte[] value() {
        return value.clone();
    }

    @Override
    public String toString() {
        return "IkIEI{len=" + value.length + "}";
    }
}