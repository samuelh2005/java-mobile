package me.samuelh2005.java_mobile.gsup.ieis;

public record SourceNameIEI(String name) {
    public SourceNameIEI {
        name = name == null ? "" : name;
    }

    public static SourceNameIEI decode(byte[] data) {
        return new SourceNameIEI(data == null ? "" : new String(data));
    }

    public static SourceNameIEI encode(String name) {
        return new SourceNameIEI(name);
    }

    public byte[] toBytes() {
        return name.getBytes();
    }

    public String toString() {
        return "SourceNameIEI{name=" + name + "}";
    }
}