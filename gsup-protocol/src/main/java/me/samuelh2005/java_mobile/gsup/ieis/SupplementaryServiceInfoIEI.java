package me.samuelh2005.java_mobile.gsup.ieis;

public record SupplementaryServiceInfoIEI(byte[] data) {
    public SupplementaryServiceInfoIEI {
        data = data == null ? new byte[0] : data.clone();
    }

    public static SupplementaryServiceInfoIEI decode(byte[] data) {
        return new SupplementaryServiceInfoIEI(data);
    }

    public static byte[] encode(SupplementaryServiceInfoIEI iei) {
        return iei.data().clone();
    }

    public String toString() {
        return "SupplementaryServiceInfoIEI{len=" + data.length + "}";
    }
}