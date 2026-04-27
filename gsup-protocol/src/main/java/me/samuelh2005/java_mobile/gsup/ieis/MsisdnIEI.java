package me.samuelh2005.java_mobile.gsup.ieis;

public record MsisdnIEI(int type, byte[] value) implements IEI {
    public MsisdnIEI {
        value = value == null ? new byte[0] : value.clone();
    }

    public MsisdnIEI(String msisdn) {
        this(0x08, encodeDigits(msisdn));
    }

    @Override
    public byte[] value() {
        return value.clone();
    }

    private static byte[] encodeDigits(String s) {
        if (s == null || s.isEmpty()) {
            return new byte[0];
        }
        String digits = s.replaceAll("[^0-9]", "");
        byte[] result = new byte[digits.length()];
        for (int i = 0; i < digits.length(); i++) {
            result[i] = (byte) (digits.charAt(i) - '0');
        }
        return result;
    }

    public String msisdn() {
        if (value == null || value.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : value) {
            sb.append(b & 0x0F);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "MsisdnIEI{msisdn=" + msisdn() + "}";
    }
}