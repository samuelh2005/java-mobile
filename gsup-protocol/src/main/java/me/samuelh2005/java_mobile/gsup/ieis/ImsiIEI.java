package me.samuelh2005.java_mobile.gsup.ieis;

public record ImsiIEI(String value) {
    public static final int CODE = 0x01;

    public ImsiIEI {
        value = value == null ? "" : value;
    }

    public static ImsiIEI decode(byte[] data) {
        if (data == null || data.length == 0) {
            return new ImsiIEI("");
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            sb.append(b & 0x0F);
        }
        return new ImsiIEI(sb.toString());
    }

    public static ImsiIEI encode(String imsi) {
        if (imsi == null || imsi.isEmpty()) {
            return new ImsiIEI("");
        }
        String digits = imsi.replaceAll("[^0-9]", "");
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
        return "ImsiIEI{imsi=" + value + "}";
    }
}