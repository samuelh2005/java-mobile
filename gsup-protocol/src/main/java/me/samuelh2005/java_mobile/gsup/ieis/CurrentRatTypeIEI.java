package me.samuelh2005.java_mobile.gsup.ieis;

public record CurrentRatTypeIEI(int type, byte[] value) implements IEI {
    public CurrentRatTypeIEI {
        value = value == null ? new byte[0] : value.clone();
    }

    public CurrentRatTypeIEI(int ratType) {
        this(0x2a, new byte[] {(byte) ratType});
    }

    @Override
    public byte[] value() {
        return value.clone();
    }

    public int ratType() {
        return value.length > 0 ? value[0] & 0xFF : 0;
    }

    @Override
    public String toString() {
        return "CurrentRatTypeIEI{ratType=" + ratType() + "}";
    }
}