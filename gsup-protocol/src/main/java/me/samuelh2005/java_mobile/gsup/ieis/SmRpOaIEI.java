package me.samuelh2005.java_mobile.gsup.ieis;

public record SmRpOaIEI(byte[] data) {
    public SmRpOaIEI {
        data = data == null ? new byte[0] : data.clone();
    }

    public static SmRpOaIEI decode(byte[] data) {
        return new SmRpOaIEI(data);
    }

    public static SmRpOaIEI encode(byte[] data) {
        return new SmRpOaIEI(data);
    }

    public byte[] toBytes() {
        return data.clone();
    }

    public String toString() {
        return "SmRpOaIEI{len=" + data.length + "}";
    }
}