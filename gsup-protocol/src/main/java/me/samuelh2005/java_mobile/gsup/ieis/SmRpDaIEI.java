package me.samuelh2005.java_mobile.gsup.ieis;

public record SmRpDaIEI(byte[] data) {
    public SmRpDaIEI {
        data = data == null ? new byte[0] : data.clone();
    }

    public static SmRpDaIEI decode(byte[] data) {
        return new SmRpDaIEI(data);
    }

    public static byte[] encode(SmRpDaIEI iei) {
        return iei.data().clone();
    }

    public String toString() {
        return "SmRpDaIEI{len=" + data.length + "}";
    }
}