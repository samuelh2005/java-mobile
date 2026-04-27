package me.samuelh2005.java_mobile.gsup.ieis;

public record AccessPointNameIEI(int type, byte[] value) implements IEI {
    public AccessPointNameIEI {
        value = value == null ? new byte[0] : value.clone();
    }

    public AccessPointNameIEI(String apn) {
        this(0x12, apn.getBytes());
    }

    @Override
    public byte[] value() {
        return value.clone();
    }

    public String apn() {
        return new String(value);
    }

    @Override
    public String toString() {
        return "AccessPointNameIEI{apn=" + apn() + "}";
    }
}