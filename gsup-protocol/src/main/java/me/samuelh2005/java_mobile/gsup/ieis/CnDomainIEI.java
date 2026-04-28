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

    private final Type value;

    public CnDomainIEI(Type value) {
        this.value = value;
    }

    public static CnDomainIEI decode(byte[] data) {
        return new CnDomainIEI(Type.fromValue(data != null && data.length > 0 ? data[0] & 0xFF : 0));
    }

    public static byte[] encode(CnDomainIEI iei) {
        return new byte[] {(byte) iei.value.value};
    }

    public String toString() {
        return "CnDomainIEI{" + value + "}";
    }
}