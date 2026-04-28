package me.samuelh2005.java_mobile.gsup.ieis;

public record RandIEI(byte[] data) {
    public RandIEI {
        if (data == null || data.length != 16) {
            throw new IllegalArgumentException("RAND must be 16 bytes");
        }
        data = data.clone();
    }

    public static RandIEI decode(byte[] data) {
        return new RandIEI(data);
    }

    public static RandIEI encode(byte[] data) {
        return new RandIEI(data);
    }

    public byte[] toBytes() {
        return data.clone();
    }

    public String toString() {
        return "RandIEI{len=" + data.length + "}";
    }
}