package me.samuelh2005.java_mobile.gsup.ieis;

public record MessageClassIEI(int type, byte[] value) implements IEI {
    public MessageClassIEI {
        value = value == null ? new byte[0] : value.clone();
    }

    public MessageClassIEI(int msgClass) {
        this(0x0a, new byte[] {(byte) msgClass});
    }

    @Override
    public byte[] value() {
        return value.clone();
    }

    public int messageClass() {
        return value.length > 0 ? value[0] & 0xFF : 0;
    }

    @Override
    public String toString() {
        return "MessageClassIEI{class=" + messageClass() + "}";
    }
}