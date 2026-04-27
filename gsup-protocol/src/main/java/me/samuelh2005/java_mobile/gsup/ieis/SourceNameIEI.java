package me.samuelh2005.java_mobile.gsup.ieis;

public record SourceNameIEI(int type, byte[] value) implements IEI {
    public SourceNameIEI {
        value = value == null ? new byte[0] : value.clone();
    }

    public SourceNameIEI(String name) {
        this(0x60, name.getBytes());
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
        return "SourceNameIEI{name=" + name() + "}";
    }
}