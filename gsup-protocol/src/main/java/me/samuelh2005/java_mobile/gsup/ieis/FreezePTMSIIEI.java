package me.samuelh2005.java_mobile.gsup.ieis;

public record FreezePTMSIIEI(int type, byte[] value) implements IEI {
    public FreezePTMSIIEI {
        value = new byte[0];
    }

    @Override
    public String toString() {
        return "FreezePTMSIIEI{}";
    }
}