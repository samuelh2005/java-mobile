package me.samuelh2005.java_mobile.gsup.ieis;

public record ImeiCheckResultIEI(int type, byte[] value) implements IEI {
    public ImeiCheckResultIEI {
        value = value == null ? new byte[0] : value.clone();
    }

    public ImeiCheckResultIEI(int result) {
        this(0x51, new byte[] {(byte) result});
    }

    @Override
    public byte[] value() {
        return value.clone();
    }

    public int result() {
        return value.length > 0 ? value[0] & 0xFF : 0;
    }

    @Override
    public String toString() {
        return "ImeiCheckResultIEI{result=" + result() + "}";
    }
}