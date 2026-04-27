package me.samuelh2005.java_mobile.gsup.ieis;

public record ImeiIEI(int type, byte[] value) implements IEI {
    public ImeiIEI {
        value = value == null ? new byte[0] : value.clone();
    }

    public ImeiIEI(String imei) {
        this(0x50, encodeImei(imei));
    }

    @Override
    public byte[] value() {
        return value.clone();
    }

    private static byte[] encodeImei(String imei) {
        if (imei == null || imei.isEmpty()) {
            return new byte[0];
        }
        String digits = imei.replaceAll("[^0-9]", "");
        byte[] result = new byte[digits.length()];
        for (int i = 0; i < digits.length(); i++) {
            result[i] = (byte) (digits.charAt(i) - '0');
        }
        return result;
    }

    public String imei() {
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
        return "ImeiIEI{imei=" + imei() + "}";
    }
}