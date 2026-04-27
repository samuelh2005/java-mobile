package me.samuelh2005.java_mobile.gsup.ieis;

public record SessionStateIEI(int type, byte[] value) implements IEI {
    public SessionStateIEI {
        value = value == null ? new byte[0] : value.clone();
    }

    public SessionStateIEI(int state) {
        this(0x31, new byte[] {(byte) state});
    }

    @Override
    public byte[] value() {
        return value.clone();
    }

    public int state() {
        return value.length > 0 ? value[0] & 0xFF : 0;
    }

    @Override
    public String toString() {
        return "SessionStateIEI{state=" + state() + "}";
    }
}