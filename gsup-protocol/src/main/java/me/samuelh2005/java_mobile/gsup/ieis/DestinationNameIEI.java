package me.samuelh2005.java_mobile.gsup.ieis;

public record DestinationNameIEI(String name) {
    public DestinationNameIEI {
        name = name == null ? "" : name;
    }

    public static DestinationNameIEI decode(byte[] data) {
        return new DestinationNameIEI(data == null ? "" : new String(data));
    }

    public static DestinationNameIEI encode(String name) {
        return new DestinationNameIEI(name);
    }

    public byte[] toBytes() {
        return name.getBytes();
    }

    public String toString() {
        return "DestinationNameIEI{name=" + name + "}";
    }
}