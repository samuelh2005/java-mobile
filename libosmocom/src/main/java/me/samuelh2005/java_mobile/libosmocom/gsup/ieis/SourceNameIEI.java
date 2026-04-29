package me.samuelh2005.java_mobile.libosmocom.gsup.ieis;

public record SourceNameIEI(String name) {
    public SourceNameIEI {
        name = name == null ? "" : name;
    }

    public static SourceNameIEI decode(byte[] data) {
        return new SourceNameIEI(data == null ? "" : new String(data));
    }

    public static byte[] encode(SourceNameIEI iei) {
        return iei.name.getBytes();
    }

    public String toString() {
        return "SourceNameIEI{name=" + name + "}";
    }
}