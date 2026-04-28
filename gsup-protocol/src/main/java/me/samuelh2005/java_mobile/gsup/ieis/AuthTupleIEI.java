package me.samuelh2005.java_mobile.gsup.ieis;

public record AuthTupleIEI(byte[] data) {
    public AuthTupleIEI {
        data = data == null ? new byte[0] : data.clone();
    }

    public static AuthTupleIEI decode(byte[] data) {
        return new AuthTupleIEI(data);
    }

    public static byte[] encode(AuthTupleIEI iei) {
        return iei.data().clone();
    }

    public String toString() {
        return "AuthTupleIEI{len=" + data.length + "}";
    }
}