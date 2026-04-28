package me.samuelh2005.java_mobile.gsup.ieis;

public record MsisdnIEI(String value) {
    public static final int CODE = 0x08;

    public MsisdnIEI {
        value = value == null ? "" : value;
    }

    public static MsisdnIEI decode(byte[] data) {
        if (data == null || data.length == 0) {
            return new MsisdnIEI("");
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            sb.append(b & 0x0F);
        }
        return new MsisdnIEI(sb.toString());
    }

    public static MsisdnIEI encode(String msisdn) {
        if (msisdn == null || msisdn.isEmpty()) {
            return new MsisdnIEI("");
        }
        String digits = msisdn.replaceAll("[^0-9]", "");
        byte[] result = new byte[digits.length()];
        for (int i = 0; i < digits.length(); i++) {
            result[i] = (byte) (digits.charAt(i) - '0');
        }
        return decode(result);
    }

    public byte[] toBytes() {
        if (value == null || value.isEmpty()) {
            return new byte[0];
        }
        String digits = value.replaceAll("[^0-9]", "");
        byte[] result = new byte[digits.length()];
        for (int i = 0; i < digits.length(); i++) {
            result[i] = (byte) (digits.charAt(i) - '0');
        }
        return result;
    }

    public String toString() {
        return "MsisdnIEI{msisdn=" + value + "}";
    }
}