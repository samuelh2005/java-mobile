package me.samuelh2005.java_mobile.gsup.ieis;

public record SresIEI(byte[] data) {
    public SresIEI {
        if (data == null || data.length != 4) {
            throw new IllegalArgumentException("SRES must be 4 bytes");
        }
        data = data.clone();
    }

    public static SresIEI decode(byte[] data) {
        return new SresIEI(data);
    }

    public static SresIEI encode(byte[] data) {
        return new SresIEI(data);
    }

    public byte[] toBytes() {
        return data.clone();
    }

    public String toString() {
        return "SresIEI{len=" + data.length + "}";
    }
}