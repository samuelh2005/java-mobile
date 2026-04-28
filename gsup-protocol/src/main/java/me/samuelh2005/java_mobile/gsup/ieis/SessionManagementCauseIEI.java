package me.samuelh2005.java_mobile.gsup.ieis;

public record SessionManagementCauseIEI(int cause) {
    public static SessionManagementCauseIEI decode(byte[] data) {
        return new SessionManagementCauseIEI(data != null && data.length > 0 ? data[0] & 0xFF : 0);
    }

    public static byte[] encode(SessionManagementCauseIEI iei) {
        return new byte[] {(byte) iei.cause()};
    }

    public String toString() {
        return "SessionManagementCauseIEI{cause=" + cause + "}";
    }
}