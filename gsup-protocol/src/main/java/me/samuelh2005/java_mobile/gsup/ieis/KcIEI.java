package me.samuelh2005.java_mobile.gsup.ieis;

public record KcIEI(byte[] data) {
    public KcIEI {
        if (data == null || data.length != 8) {
            throw new IllegalArgumentException("Kc must be 8 bytes");
        }
        data = data.clone();
    }

    public static KcIEI decode(byte[] data) {
        return new KcIEI(data);
    }

    public static KcIEI encode(byte[] data) {
        return new KcIEI(data);
    }

    public byte[] toBytes() {
        return data.clone();
    }

    public String toString() {
        return "KcIEI{len=" + data.length + "}";
    }
}