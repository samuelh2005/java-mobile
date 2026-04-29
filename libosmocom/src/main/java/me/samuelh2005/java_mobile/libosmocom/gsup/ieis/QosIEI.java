package me.samuelh2005.java_mobile.libosmocom.gsup.ieis;

public record QosIEI(byte[] data) {
    public QosIEI {
        data = data == null ? new byte[0] : data.clone();
    }

    public static QosIEI decode(byte[] data) {
        return new QosIEI(data);
    }

    public static byte[] encode(QosIEI iei) {
        return iei.data().clone();
    }

    public String toString() {
        return "QosIEI{len=" + data.length + "}";
    }
}