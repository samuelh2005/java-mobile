package me.samuelh2005.java_mobile.gsup.ieis;

public record PcoIEI(byte[] data) {
    public PcoIEI {
        data = data == null ? new byte[0] : data.clone();
    }

    public static PcoIEI decode(byte[] data) {
        return new PcoIEI(data);
    }

    public static byte[] encode(PcoIEI iei) {
        return iei.data().clone();
    }

    public String toString() {
        return "PcoIEI{len=" + data.length + "}";
    }
}