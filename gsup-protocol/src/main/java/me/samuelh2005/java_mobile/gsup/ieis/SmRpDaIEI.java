package me.samuelh2005.java_mobile.gsup.ieis;

public record SmRpDaIEI(int type, byte[] value) implements IEI {
    public SmRpDaIEI {
        value = value == null ? new byte[0] : value.clone();
    }

    @Override
    public byte[] value() {
        return value.clone();
    }

    @Override
    public String toString() {
        return "SmRpDaIEI{len=" + value.length + "}";
    }
}