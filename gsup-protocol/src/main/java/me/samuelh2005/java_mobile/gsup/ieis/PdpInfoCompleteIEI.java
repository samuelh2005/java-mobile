package me.samuelh2005.java_mobile.gsup.ieis;

public record PdpInfoCompleteIEI(int type, byte[] value) implements IEI {
    public PdpInfoCompleteIEI {
        value = new byte[0];
    }

    @Override
    public String toString() {
        return "PdpInfoCompleteIEI{}";
    }
}