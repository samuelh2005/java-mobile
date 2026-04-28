package me.samuelh2005.java_mobile.gsup.ieis;

public record MsisdnIEI(String value) {
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

    public static byte[] encode(MsisdnIEI iei) {
        if (iei.value == null || iei.value.isEmpty()) {
            return new byte[0];
        }
        String digits = iei.value.replaceAll("[^0-9]", "");
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