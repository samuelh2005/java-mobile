package me.samuelh2005.java_mobile.gsup.ieis;

public record SmRpCauseIEI(int type, byte[] value) implements IEI {
    public SmRpCauseIEI {
        value = value == null ? new byte[0] : value.clone();
    }

    public SmRpCauseIEI(int cause) {
        this(0x44, new byte[] {(byte) cause});
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
        return "SmRpCauseIEI{cause=" + cause() + "}";
    }
}