package me.samuelh2005.java_mobile.gsup.ieis;

public final class MessageClassIEI {
    public enum Type {
        BSSAP(0x01),
        RANAP(0x02);

        public final int value;

        Type(int value) {
            this.value = value;
        }

        public static Type fromValue(int value) {
            for (Type m : values()) {
                if (m.value == value) return m;
            }
            return BSSAP;
        }
    }

    public static final int CODE = 0x0a;

    private final Type value;

    public MessageClassIEI(Type value) {
        this.value = value;
    }

    public static MessageClassIEI decode(byte[] data) {
        return new MessageClassIEI(Type.fromValue(data != null && data.length > 0 ? data[0] & 0xFF : 0));
    }

    public static MessageClassIEI encode(Type messageClass) {
        return new MessageClassIEI(messageClass);
    }

    public Type value() {
        return value;
    }

    public byte[] toBytes() {
        return new byte[] {(byte) value.value};
    }

    public String toString() {
        return "MessageClassIEI{" + value + "}";
    }
}