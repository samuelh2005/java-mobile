package me.samuelh2005.java_mobile.gsup.ieis;

public record PcoIEI(byte[] data) {
    public PcoIEI {
        data = data == null ? new byte[0] : data.clone();
    }

    public static PcoIEI decode(byte[] data) {
        return new PcoIEI(data);
    }

    public static PcoIEI encode(byte[] data) {
        return new PcoIEI(data);
    }

    public byte[] toBytes() {
        return data.clone();
    }

    public String toString() {
        return "PcoIEI{len=" + data.length + "}";
    }
}