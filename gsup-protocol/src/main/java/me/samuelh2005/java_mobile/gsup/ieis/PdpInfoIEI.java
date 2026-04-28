package me.samuelh2005.java_mobile.gsup.ieis;

public record PdpInfoIEI(byte[] data) {
    public PdpInfoIEI {
        data = data == null ? new byte[0] : data.clone();
    }

    public static PdpInfoIEI decode(byte[] data) {
        return new PdpInfoIEI(data);
    }

    public static byte[] encode(PdpInfoIEI iei) {
        return iei.data().clone();
    }

    public String toString() {
        return "PdpInfoIEI{len=" + data.length + "}";
    }
}