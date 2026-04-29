package me.samuelh2005.java_mobile.libosmocom.gsup.ieis;

public record AccessPointNameIEI(String apn) {
    public AccessPointNameIEI {
        apn = apn == null ? "" : apn;
    }

    public static AccessPointNameIEI decode(byte[] data) {
        return new AccessPointNameIEI(data == null ? "" : new String(data));
    }

    public static byte[] encode(AccessPointNameIEI iei) {
        return iei.apn().getBytes();
    }

    public String toString() {
        return "AccessPointNameIEI{apn=" + apn + "}";
    }
}