package me.samuelh2005.java_mobile.libosmocom.gsup.ieis;

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
        if (data == null || data.length < 1) {
            return new ImeiCheckResultIEI(Type.ACK);
        }
        int wireValue = data[0] & 0xFF;
        return new ImeiCheckResultIEI(Type.fromValue(wireValue + 1));
    }

    public static byte[] encode(ImeiCheckResultIEI iei) {
        int wireValue = iei.value.value - 1;
        return new byte[] {(byte) wireValue};
    }

    public String toString() {
        return "ImeiCheckResultIEI{" + value + "}";
    }
}