package me.samuelh2005.java_mobile.libosmocom.gsup.ieis;

public final class FreezePTMSIIEI {
    public static FreezePTMSIIEI decode(byte[] data) {
        return new FreezePTMSIIEI();
    }

    public static byte[] encode(FreezePTMSIIEI iei) {
        return new byte[0];
    }

    public String toString() {
        return "FreezePTMSIIEI{}";
    }
}