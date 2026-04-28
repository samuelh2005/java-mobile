package me.samuelh2005.java_mobile.gsup.ieis;

public final class SessionStateIEI {
    public enum Type {
        UNDEFINED(0x00),
        BEGIN(0x01),
        CONTINUE(0x02),
        END(0x03);

        public final int value;

        Type(int value) {
            this.value = value;
        }

        public static Type fromValue(int value) {
            for (Type s : values()) {
                if (s.value == value) return s;
            }
            return UNDEFINED;
        }
    }

    public static final int CODE = 0x31;

    private final Type value;

    public SessionStateIEI(Type value) {
        this.value = value;
    }

    public static SessionStateIEI decode(byte[] data) {
        return new SessionStateIEI(Type.fromValue(data != null && data.length > 0 ? data[0] & 0xFF : 0));
    }

    public static SessionStateIEI encode(Type state) {
        return new SessionStateIEI(state);
    }

    public Type value() {
        return value;
    }

    public byte[] toBytes() {
        return new byte[] {(byte) value.value};
    }

    public String toString() {
        return "SessionStateIEI{" + value + "}";
    }
}