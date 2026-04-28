package me.samuelh2005.java_mobile.gsup.ieis;

public record DestinationNameIEI(String name) {
    public DestinationNameIEI {
        name = name == null ? "" : name;
    }

    public static DestinationNameIEI decode(byte[] data) {
        return new DestinationNameIEI(data == null ? "" : new String(data));
    }

    public static byte[] encode(DestinationNameIEI iei) {
        return iei.name().getBytes();
    }

    public String toString() {
        return "DestinationNameIEI{name=" + name + "}";
    }
}