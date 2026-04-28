package me.samuelh2005.java_mobile.gsup.ieis;

public record ResIEI(byte[] data) {
    public ResIEI {
        if (data == null) {
            throw new IllegalArgumentException("RES cannot be null");
        }
        data = data.clone();
    }

    public static ResIEI decode(byte[] data) {
        return new ResIEI(data);
    }

    public static byte[] encode(ResIEI iei) {
        return iei.data().clone();
    }

    public String toString() {
        return "ResIEI{len=" + data.length + "}";
    }
}