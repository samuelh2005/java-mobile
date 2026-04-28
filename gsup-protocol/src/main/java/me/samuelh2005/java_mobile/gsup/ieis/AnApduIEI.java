package me.samuelh2005.java_mobile.gsup.ieis;

public record AnApduIEI(byte[] data) {
    public AnApduIEI {
        data = data == null ? new byte[0] : data.clone();
    }

    public static AnApduIEI decode(byte[] data) {
        return new AnApduIEI(data);
    }

    public static AnApduIEI encode(byte[] data) {
        return new AnApduIEI(data);
    }

    public byte[] toBytes() {
        return data.clone();
    }

    public String toString() {
        return "AnApduIEI{len=" + data.length + "}";
    }
}