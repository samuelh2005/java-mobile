package me.samuelh2005.java_mobile.gsup.ieis;

public final class MessageClassIEI {
    public enum Type {
        UNSET(0x00),
        SUBSCRIBER_MANAGEMENT(0x01),
        SMS(0x02),
        USSD(0x03),
        INTER_MSC(0x04),
        IPSEC_EPDG(0x05);

        public final int value;

        Type(int value) {
            this.value = value;
        }

        public static Type fromValue(int value) {
            for (Type m : values()) {
                if (m.value == value) return m;
            }
            return UNSET;
        }
    }

    private final Type value;

    public MessageClassIEI(Type value) {
        this.value = value;
    }

    public static MessageClassIEI decode(byte[] data) {
        return new MessageClassIEI(Type.fromValue(data != null && data.length > 0 ? data[0] & 0xFF : 0));
    }

    public static byte[] encode(MessageClassIEI iei) {
        return new byte[] {(byte) iei.value.value};
    }

    public String toString() {
        return "MessageClassIEI{" + value + "}";
    }
}