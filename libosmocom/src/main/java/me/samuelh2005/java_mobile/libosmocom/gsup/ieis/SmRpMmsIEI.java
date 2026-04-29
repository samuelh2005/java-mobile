package me.samuelh2005.java_mobile.libosmocom.gsup.ieis;

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

    private final Type value;

    public SmRpMmsIEI(Type value) {
        this.value = value;
    }

    public static SmRpMmsIEI decode(byte[] data) {
        return new SmRpMmsIEI(Type.fromValue(data != null && data.length > 0 ? data[0] & 0xFF : 0));
    }

    public static byte[] encode(SmRpMmsIEI iei) {
        return new byte[] {(byte) iei.value.value};
    }

    public String toString() {
        return "SmRpMmsIEI{" + value + "}";
    }
}