package me.samuelh2005.java_mobile.libosmocom.gsup.ieis;

public record SmRpUiIEI(byte[] data) {
    public SmRpUiIEI {
        data = data == null ? new byte[0] : data.clone();
    }

    public static SmRpUiIEI decode(byte[] data) {
        return new SmRpUiIEI(data);
    }

    public static byte[] encode(SmRpUiIEI iei) {
        return iei.data().clone();
    }

    public String toString() {
        return "SmRpUiIEI{len=" + data.length + "}";
    }
}