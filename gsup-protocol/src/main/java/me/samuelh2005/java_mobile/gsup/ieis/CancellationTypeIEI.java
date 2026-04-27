package me.samuelh2005.java_mobile.gsup.ieis;

public record CancellationTypeIEI(int type, byte[] value) implements IEI {
    public CancellationTypeIEI {
        value = value == null ? new byte[0] : value.clone();
    }

    public CancellationTypeIEI(int cancelType) {
        this(0x06, new byte[] {(byte) cancelType});
    }

    @Override
    public byte[] value() {
        return value.clone();
    }

    public int cancellationType() {
        return value.length > 0 ? value[0] & 0xFF : 0;
    }

    @Override
    public String toString() {
        return "CancellationTypeIEI{type=" + cancellationType() + "}";
    }
}