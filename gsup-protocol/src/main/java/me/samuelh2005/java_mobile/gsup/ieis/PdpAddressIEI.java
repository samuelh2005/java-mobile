package me.samuelh2005.java_mobile.gsup.ieis;

public record PdpAddressIEI(byte[] data) {
    public PdpAddressIEI {
        data = data == null ? new byte[0] : data.clone();
    }

    public static PdpAddressIEI decode(byte[] data) {
        return new PdpAddressIEI(data);
    }

    public static PdpAddressIEI encode(byte[] data) {
        return new PdpAddressIEI(data);
    }

    public byte[] toBytes() {
        return data.clone();
    }

    public String toString() {
        return "PdpAddressIEI{len=" + data.length + "}";
    }
}