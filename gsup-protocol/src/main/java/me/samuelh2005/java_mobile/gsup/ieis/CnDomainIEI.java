package me.samuelh2005.java_mobile.gsup.ieis;

public final class CnDomainIEI {
    public enum Type {
        PS(0x01),
        CS(0x02);

        public final int value;

        Type(int value) {
            this.value = value;
        }

        public static Type fromValue(int value) {
            for (Type d : values()) {
                if (d.value == value) return d;
            }
            return PS;
        }
    }

    public static final int CODE = 0x28;

    private final Type value;

    public CnDomainIEI(Type value) {
        this.value = value;
    }

    public static CnDomainIEI decode(byte[] data) {
        return new CnDomainIEI(Type.fromValue(data != null && data.length > 0 ? data[0] & 0xFF : 0));
    }

    public static CnDomainIEI encode(Type domain) {
        return new CnDomainIEI(domain);
    }

    public Type value() {
        return value;
    }

    public byte[] toBytes() {
        return new byte[] {(byte) value.value};
    }

    public String toString() {
        return "CnDomainIEI{" + value + "}";
    }
}