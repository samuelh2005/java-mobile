package me.samuelh2005.java_mobile.gsup.ieis;

public record SmRpMmsIEI(int type, byte[] value) implements IEI {
    public SmRpMmsIEI {
        value = value == null ? new byte[0] : value.clone();
    }

    public SmRpMmsIEI(int mms) {
        this(0x45, new byte[] {(byte) mms});
    }

    @Override
    public byte[] value() {
        return value.clone();
    }

    public int mms() {
        return value.length > 0 ? value[0] & 0xFF : 0;
    }

    @Override
    public String toString() {
        return "SmRpMmsIEI{mms=" + mms() + "}";
    }
}