package me.samuelh2005.java_mobile.gsup.ieis;

public final class SmAlertReasonIEI {
    public enum Type {
        MS_PRESENT(0x01),
        MEMORY_AVAILABLE(0x02);

        public final int value;

        Type(int value) {
            this.value = value;
        }

        public static Type fromValue(int value) {
            for (Type r : values()) {
                if (r.value == value) return r;
            }
            return MS_PRESENT;
        }
    }

    private final Type value;

    public SmAlertReasonIEI(Type value) {
        this.value = value;
    }

    public static SmAlertReasonIEI decode(byte[] data) {
        return new SmAlertReasonIEI(Type.fromValue(data != null && data.length > 0 ? data[0] & 0xFF : 0));
    }

    public static byte[] encode(SmAlertReasonIEI iei) {
        return new byte[] {(byte) iei.value.value};
    }

    public String toString() {
        return "SmAlertReasonIEI{" + value + "}";
    }
}