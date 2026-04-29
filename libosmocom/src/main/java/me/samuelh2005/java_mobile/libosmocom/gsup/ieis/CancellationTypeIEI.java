package me.samuelh2005.java_mobile.libosmocom.gsup.ieis;

public record CancellationTypeIEI(int cancelType) {
    public static CancellationTypeIEI decode(byte[] data) {
        if (data == null || data.length < 1) {
            return new CancellationTypeIEI(0);
        }
        int wireValue = data[0] & 0xFF;
        return new CancellationTypeIEI(wireValue + 1);
    }

    public static byte[] encode(CancellationTypeIEI iei) {
        int wireValue = iei.cancelType() - 1;
        return new byte[] {(byte) wireValue};
    }

    public String toString() {
        return "CancellationTypeIEI{type=" + cancelType + "}";
    }
}