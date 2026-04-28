package me.samuelh2005.java_mobile.gsup.ieis;

public record PdpContextIdIEI(int contextId) {

    public static PdpContextIdIEI decode(byte[] data) {
        return new PdpContextIdIEI(data != null && data.length > 0 ? data[0] & 0xFF : 0);
    }

    public static byte[] encode(PdpContextIdIEI iei) {
        return new byte[] {(byte) iei.contextId()};
    }

    public String toString() {
        return "PdpContextIdIEI{id=" + contextId + "}";
    }
}