package me.samuelh2005.java_mobile.gsup.ieis;

public record IkIEI(byte[] data) {
    public IkIEI {
        if (data == null || data.length != 16) {
            throw new IllegalArgumentException("IK must be 16 bytes");
        }
        data = data.clone();
    }

    public static IkIEI decode(byte[] data) {
        return new IkIEI(data);
    }

    public static IkIEI encode(byte[] data) {
        return new IkIEI(data);
    }

    public byte[] toBytes() {
        return data.clone();
    }

    public String toString() {
        return "IkIEI{len=" + data.length + "}";
    }
}