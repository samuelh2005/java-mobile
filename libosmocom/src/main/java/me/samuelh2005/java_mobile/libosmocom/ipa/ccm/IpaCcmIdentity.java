package me.samuelh2005.java_mobile.libosmocom.ipa.ccm;

import java.util.Objects;

public record IpaCcmIdentity(
        String serialNumber,
        String unitName,
        String location1,
        String location2,
        String equipmentVersion,
        String softwareVersion,
        String unitId,
        String macAddress) {

    public IpaCcmIdentity {
        serialNumber = normalize(serialNumber, "java-mobile-msc");
        unitName = normalize(unitName, serialNumber);
        location1 = normalize(location1, "");
        location2 = normalize(location2, "");
        equipmentVersion = normalize(equipmentVersion, "");
        softwareVersion = normalize(softwareVersion, "");
        unitId = normalize(unitId, "0/0/0");
        macAddress = normalize(macAddress, "00:00:00:00:00:00");
    }

    public static IpaCcmIdentity defaultIdentity() {
        return new IpaCcmIdentity(null, null, null, null, null, null, null, null);
    }

    public String valueFor(IpaCcmIdTag tag) {
        return switch (tag) {
            case SERNR -> serialNumber;
            case UNITNAME -> unitName;
            case LOCATION1 -> location1;
            case LOCATION2 -> location2;
            case EQUIPVERS -> equipmentVersion;
            case SWVERSION -> softwareVersion;
            case IPADDR -> "";
            case MACADDR -> macAddress;
            case UNIT -> unitId;
            case USERNAME -> "";
            case PASSWORD -> "";
            case ACCESS_CLASS -> "";
            case APP_PROTO_VER -> "";
        };
    }

    private static String normalize(String value, String fallback) {
        String resolved = Objects.requireNonNullElse(value, fallback);
        return resolved == null ? "" : resolved;
    }
}
