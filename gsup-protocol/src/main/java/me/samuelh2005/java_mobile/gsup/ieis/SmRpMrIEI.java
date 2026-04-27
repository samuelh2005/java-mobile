package me.samuelh2005.java_mobile.gsup.ieis;

public record SmRpMrIEI(int type, byte[] value) implements IEI {
    public SmRpMrIEI {
        value = value == null ? new byte[0] : value.clone();
    }

    public SmRpMrIEI(int messageRef) {
        this(0x40, new byte[] {(byte) messageRef});
    }

    @Override
    public byte[] value() {
        return value.clone();
    }

    public int messageReference() {
        return value.length > 0 ? value[0] & 0xFF : 0;
    }

    @Override
    public String toString() {
        return "SmRpMrIEI{mr=" + messageReference() + "}";
    }
}