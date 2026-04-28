package me.samuelh2005.java_mobile.gsup.ieis;

public record SessionIdIEI(int sessionId) {
    public static SessionIdIEI decode(byte[] data) {
        if (data == null || data.length != 4) {
            return new SessionIdIEI(0);
        }
        int id = ((data[0] & 0xFF) << 24) | ((data[1] & 0xFF) << 16) | ((data[2] & 0xFF) << 8) | (data[3] & 0xFF);
        return new SessionIdIEI(id);
    }

    public static SessionIdIEI encode(int sessionId) {
        return new SessionIdIEI(sessionId);
    }

    public byte[] toBytes() {
        return new byte[] {
            (byte) ((sessionId >> 24) & 0xFF),
            (byte) ((sessionId >> 16) & 0xFF),
            (byte) ((sessionId >> 8) & 0xFF),
            (byte) (sessionId & 0xFF)
        };
    }

    public String toString() {
        return "SessionIdIEI{id=" + sessionId + "}";
    }
}