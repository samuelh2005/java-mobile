package me.samuelh2005.java_mobile.libosmocom.gsup.ieis;

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

    public static byte[] encode(IkIEI iei) {
        return iei.data().clone();
    }

    public String toString() {
        return "IkIEI{len=" + data.length + "}";
    }
}