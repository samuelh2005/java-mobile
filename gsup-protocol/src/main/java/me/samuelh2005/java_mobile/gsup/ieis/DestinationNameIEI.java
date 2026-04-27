package me.samuelh2005.java_mobile.gsup.ieis;

public record DestinationNameIEI(int type, byte[] value) implements IEI {
    public DestinationNameIEI {
        value = value == null ? new byte[0] : value.clone();
    }

    public DestinationNameIEI(String name) {
        this(0x61, name.getBytes());
    }

    @Override
    public byte[] value() {
        return value.clone();
    }

    public String name() {
        return new String(value);
    }

    @Override
    public String toString() {
        return "DestinationNameIEI{name=" + name() + "}";
    }
}