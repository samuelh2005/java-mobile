package me.samuelh2005.java_mobile.gsup.ieis;

public record AuthTupleIEI(int type, byte[] value) implements IEI {
    public AuthTupleIEI {
        value = value == null ? new byte[0] : value.clone();
    }

    @Override
    public byte[] value() {
        return value.clone();
    }

    @Override
    public String toString() {
        return "AuthTupleIEI{len=" + value.length + "}";
    }
}