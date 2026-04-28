package me.samuelh2005.java_mobile.gsup.ieis;

public final class PdpInfoCompleteIEI {
    public static PdpInfoCompleteIEI decode(byte[] data) {
        return new PdpInfoCompleteIEI();
    }

    public static byte[] encode(PdpInfoCompleteIEI iei) {
        return new byte[0];
    }

    public String toString() {
        return "PdpInfoCompleteIEI{}";
    }
}