package me.samuelh2005.java_mobile.gsup.ieis;

public record CkIEI(byte[] data) {
    public CkIEI {
        if (data == null || data.length != 16) {
            throw new IllegalArgumentException("CK must be 16 bytes");
        }
        data = data.clone();
    }

    public static CkIEI decode(byte[] data) {
        return new CkIEI(data);
    }

    public static CkIEI encode(byte[] data) {
        return new CkIEI(data);
    }

    public byte[] toBytes() {
        return data.clone();
    }

    public String toString() {
        return "CkIEI{len=" + data.length + "}";
    }
}