package me.samuelh2005.java_mobile.gsup.ieis;

public record CauseIEI(int cause) {
    public static final int CODE = 0x02;

    public static CauseIEI decode(byte[] data) {
        return new CauseIEI(data != null && data.length > 0 ? data[0] & 0xFF : 0);
    }

    public static CauseIEI encode(int cause) {
        return new CauseIEI(cause);
    }

    public int code() {
        return CODE;
    }

    public Integer value() {
        return cause;
    }

    public byte[] toBytes() {
        return new byte[] {(byte) cause};
    }

    public String toString() {
        return "CauseIEI{cause=" + cause + "}";
    }
}