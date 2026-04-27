package me.samuelh2005.java_mobile.gsup.ieis;

public record PdpContextIdIEI(int type, byte[] value) implements IEI {
    public PdpContextIdIEI {
        value = value == null ? new byte[0] : value.clone();
    }

    public PdpContextIdIEI(int contextId) {
        this(0x10, new byte[] {(byte) contextId});
    }

    @Override
    public byte[] value() {
        return value.clone();
    }

    public int contextId() {
        return value.length > 0 ? value[0] & 0xFF : 0;
    }

    @Override
    public String toString() {
        return "PdpContextIdIEI{id=" + contextId() + "}";
    }
}