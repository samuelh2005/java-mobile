package me.samuelh2005.java_mobile.libosmocom.gsup.ieis;

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

    private final Type value;

    public SessionStateIEI(Type value) {
        this.value = value;
    }

    public static SessionStateIEI decode(byte[] data) {
        return new SessionStateIEI(Type.fromValue(data != null && data.length > 0 ? data[0] & 0xFF : 0));
    }

    public static byte[] encode(SessionStateIEI iei) {
        return new byte[] {(byte) iei.value.value};
    }

    public String toString() {
        return "SessionStateIEI{" + value + "}";
    }
}