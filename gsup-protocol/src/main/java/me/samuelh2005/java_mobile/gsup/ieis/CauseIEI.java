package me.samuelh2005.java_mobile.gsup.ieis;

public record CauseIEI(int type, byte[] value) implements IEI {
    public CauseIEI {
        value = value == null ? new byte[0] : value.clone();
    }

    public CauseIEI(int cause) {
        this(0x02, new byte[] {(byte) cause});
    }

    @Override
    public byte[] value() {
        return value.clone();
    }

    public int cause() {
        return value.length > 0 ? value[0] & 0xFF : 0;
    }

    @Override
    public String toString() {
        return "CauseIEI{cause=" + cause() + "}";
    }
}