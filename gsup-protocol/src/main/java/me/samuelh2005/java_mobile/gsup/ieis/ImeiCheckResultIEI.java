package me.samuelh2005.java_mobile.gsup.ieis;

public final class ImeiCheckResultIEI {
    public enum Type {
        ACK(0x01),
        NACK(0x02);

        public final int value;

        Type(int value) {
            this.value = value;
        }

        public static Type fromValue(int value) {
            for (Type r : values()) {
                if (r.value == value) return r;
            }
            return ACK;
        }
    }

    private final Type value;

    public ImeiCheckResultIEI(Type value) {
        this.value = value;
    }

    public static ImeiCheckResultIEI decode(byte[] data) {
        return new ImeiCheckResultIEI(Type.fromValue(data != null && data.length > 0 ? data[0] & 0xFF : 0));
    }

    public static byte[] encode(ImeiCheckResultIEI iei) {
        return new byte[] {(byte) iei.value.value};
    }

    public String toString() {
        return "ImeiCheckResultIEI{" + value + "}";
    }
}