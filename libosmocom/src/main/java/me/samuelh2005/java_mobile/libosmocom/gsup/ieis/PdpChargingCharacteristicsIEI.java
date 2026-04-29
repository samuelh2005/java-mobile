package me.samuelh2005.java_mobile.libosmocom.gsup.ieis;

public record PdpChargingCharacteristicsIEI(byte[] data) {
    public PdpChargingCharacteristicsIEI {
        data = data == null ? new byte[0] : data.clone();
    }

    public static PdpChargingCharacteristicsIEI decode(byte[] data) {
        return new PdpChargingCharacteristicsIEI(data);
    }

    public static byte[] encode(PdpChargingCharacteristicsIEI iei) {
        return iei.data().clone();
    }

    public String toString() {
        return "PdpChargingCharacteristicsIEI{len=" + data.length + "}";
    }
}