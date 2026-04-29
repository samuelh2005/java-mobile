package me.samuelh2005.java_mobile.libosmocom.ipa.ccm;

public enum IpaCcmIdTag {
    SERNR(0x00),
    UNITNAME(0x01),
    LOCATION1(0x02),
    LOCATION2(0x03),
    EQUIPVERS(0x04),
    SWVERSION(0x05),
    IPADDR(0x06),
    MACADDR(0x07),
    UNIT(0x08),
    USERNAME(0x09),
    PASSWORD(0x0a),
    ACCESS_CLASS(0x0b),
    APP_PROTO_VER(0x0c);

    private final int code;

    IpaCcmIdTag(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }

    public static IpaCcmIdTag fromCode(int code) {
        int normalized = code & 0xFF;
        for (IpaCcmIdTag tag : values()) {
            if (tag.code == normalized) {
                return tag;
            }
        }
        return null;
    }
}
