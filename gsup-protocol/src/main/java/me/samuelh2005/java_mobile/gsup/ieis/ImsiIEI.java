package me.samuelh2005.java_mobile.gsup.ieis;

public record ImsiIEI(int type, byte[] value) implements IEI {
    public ImsiIEI {
        value = value == null ? new byte[0] : value.clone();
    }

    public ImsiIEI(String imsi) {
        this(0x01, encodeImsi(imsi));
    }

    @Override
    public byte[] value() {
        return value.clone();
    }

    private static byte[] encodeImsi(String imsi) {
        if (imsi == null || imsi.isEmpty()) {
            return new byte[0];
        }
        String digits = imsi.replaceAll("[^0-9]", "");
        byte[] result = new byte[digits.length()];
        for (int i = 0; i < digits.length(); i++) {
            result[i] = (byte) (digits.charAt(i) - '0');
        }
        return result;
    }

    public String imsi() {
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
        return "ImsiIEI{imsi=" + imsi() + "}";
    }
}