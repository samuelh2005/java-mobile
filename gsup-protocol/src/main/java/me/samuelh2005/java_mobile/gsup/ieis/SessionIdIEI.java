package me.samuelh2005.java_mobile.gsup.ieis;

public record SessionIdIEI(int type, byte[] value) implements IEI {
    public SessionIdIEI {
        if (value == null || value.length != 4) {
            throw new IllegalArgumentException("Session ID must be 4 bytes");
        }
        value = value.clone();
    }

    public SessionIdIEI(int sessionId) {
        this(0x30, new byte[] {
            (byte) ((sessionId >> 24) & 0xFF),
            (byte) ((sessionId >> 16) & 0xFF),
            (byte) ((sessionId >> 8) & 0xFF),
            (byte) (sessionId & 0xFF)
        });
    }

    @Override
    public byte[] value() {
        return value.clone();
    }

    public int sessionId() {
        return ((value[0] & 0xFF) << 24) | ((value[1] & 0xFF) << 16) | ((value[2] & 0xFF) << 8) | (value[3] & 0xFF);
    }

    @Override
    public String toString() {
        return "SessionIdIEI{id=" + sessionId() + "}";
    }
}