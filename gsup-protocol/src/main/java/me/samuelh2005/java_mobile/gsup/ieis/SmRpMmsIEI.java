package me.samuelh2005.java_mobile.gsup.ieis;

public final class SmRpMmsIEI {
    public enum Type {
        MORE_MESSAGES(0x01),
        NO_MORE_MESSAGES(0x00);

        public final int value;

        Type(int value) {
            this.value = value;
        }

        public static Type fromValue(int value) {
            for (Type m : values()) {
                if (m.value == value) return m;
            }
            return NO_MORE_MESSAGES;
        }
    }

    public static final int CODE = 0x45;

    private final Type value;

    public SmRpMmsIEI(Type value) {
        this.value = value;
    }

    public static SmRpMmsIEI decode(byte[] data) {
        return new SmRpMmsIEI(Type.fromValue(data != null && data.length > 0 ? data[0] & 0xFF : 0));
    }

    public static SmRpMmsIEI encode(Type mms) {
        return new SmRpMmsIEI(mms);
    }

    public Type value() {
        return value;
    }

    public byte[] toBytes() {
        return new byte[] {(byte) value.value};
    }

    public String toString() {
        return "SmRpMmsIEI{" + value + "}";
    }
}