package me.samuelh2005.java_mobile.libosmocom.gsup.ieis;

public final class CurrentRatTypeIEI {
    public enum Type {
        UNKNOWN(0x00),
        GERAN(0x01),
        UTRAN(0x02),
        EUTRAN(0x03);

        public final int value;

        Type(int value) {
            this.value = value;
        }

        public static Type fromValue(int value) {
            for (Type r : values()) {
                if (r.value == value) return r;
            }
            return UNKNOWN;
        }
    }

    private final Type value;

    public CurrentRatTypeIEI(Type value) {
        this.value = value;
    }

    public static CurrentRatTypeIEI decode(byte[] data) {
        return new CurrentRatTypeIEI(Type.fromValue(data != null && data.length > 0 ? data[0] & 0xFF : 0));
    }

    public static byte[] encode(CurrentRatTypeIEI iei) {
        return new byte[] {(byte) iei.value.value};
    }

    public String toString() {
        return "CurrentRatTypeIEI{" + value + "}";
    }
}