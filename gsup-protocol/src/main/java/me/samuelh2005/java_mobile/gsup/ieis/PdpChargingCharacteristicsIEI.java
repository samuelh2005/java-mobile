package me.samuelh2005.java_mobile.gsup.ieis;

public record PdpChargingCharacteristicsIEI(byte[] data) {
    public PdpChargingCharacteristicsIEI {
        data = data == null ? new byte[0] : data.clone();
    }

    public static PdpChargingCharacteristicsIEI decode(byte[] data) {
        return new PdpChargingCharacteristicsIEI(data);
    }

    public static PdpChargingCharacteristicsIEI encode(byte[] data) {
        return new PdpChargingCharacteristicsIEI(data);
    }

    public byte[] toBytes() {
        return data.clone();
    }

    public String toString() {
        return "PdpChargingCharacteristicsIEI{len=" + data.length + "}";
    }
}