package me.samuelh2005.java_mobile.gsup.ieis;

public record CnDomainIEI(int type, byte[] value) implements IEI {
    public CnDomainIEI {
        value = value == null ? new byte[0] : value.clone();
    }

    public CnDomainIEI(int domain) {
        this(0x28, new byte[] {(byte) domain});
    }

    @Override
    public byte[] value() {
        return value.clone();
    }

    public int domain() {
        return value.length > 0 ? value[0] & 0xFF : 0;
    }

    @Override
    public String toString() {
        return "CnDomainIEI{domain=" + domain() + "}";
    }
}