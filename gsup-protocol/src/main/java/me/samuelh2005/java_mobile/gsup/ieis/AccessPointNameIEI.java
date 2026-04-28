package me.samuelh2005.java_mobile.gsup.ieis;

public record AccessPointNameIEI(String apn) {
    public AccessPointNameIEI {
        apn = apn == null ? "" : apn;
    }

    public static AccessPointNameIEI decode(byte[] data) {
        return new AccessPointNameIEI(data == null ? "" : new String(data));
    }

    public static AccessPointNameIEI encode(String apn) {
        return new AccessPointNameIEI(apn);
    }

    public byte[] toBytes() {
        return apn.getBytes();
    }

    public String toString() {
        return "AccessPointNameIEI{apn=" + apn + "}";
    }
}