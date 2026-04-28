package me.samuelh2005.java_mobile.gsup.ieis;

public record AuthTupleIEI(byte[] data) {
    public AuthTupleIEI {
        data = data == null ? new byte[0] : data.clone();
    }

    public static AuthTupleIEI decode(byte[] data) {
        return new AuthTupleIEI(data);
    }

    public static AuthTupleIEI encode(byte[] data) {
        return new AuthTupleIEI(data);
    }

    public byte[] toBytes() {
        return data.clone();
    }

    public String toString() {
        return "AuthTupleIEI{len=" + data.length + "}";
    }
}