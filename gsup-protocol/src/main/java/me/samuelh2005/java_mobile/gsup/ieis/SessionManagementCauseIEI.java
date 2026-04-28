package me.samuelh2005.java_mobile.gsup.ieis;

public record SessionManagementCauseIEI(int cause) {
    public static SessionManagementCauseIEI decode(byte[] data) {
        return new SessionManagementCauseIEI(data != null && data.length > 0 ? data[0] & 0xFF : 0);
    }

    public static SessionManagementCauseIEI encode(int cause) {
        return new SessionManagementCauseIEI(cause);
    }

    public byte[] toBytes() {
        return new byte[] {(byte) cause};
    }

    public String toString() {
        return "SessionManagementCauseIEI{cause=" + cause + "}";
    }
}