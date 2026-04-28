package me.samuelh2005.java_mobile.gsup.ieis;

public record ImeiIEI(String imei) {
    public ImeiIEI {
        imei = imei == null ? "" : imei;
    }

    public static ImeiIEI decode(byte[] data) {
        if (data == null || data.length == 0) {
            return new ImeiIEI("");
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            sb.append(b & 0x0F);
        }
        return new ImeiIEI(sb.toString());
    }

    public static byte[] encode(ImeiIEI iei) {
        String imei = iei.imei();
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

    public String toString() {
        return "ImeiIEI{imei=" + imei + "}";
    }
}