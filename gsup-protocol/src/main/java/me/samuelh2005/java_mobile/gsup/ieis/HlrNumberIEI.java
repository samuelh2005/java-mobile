package me.samuelh2005.java_mobile.gsup.ieis;

public record HlrNumberIEI(String number) {
    public HlrNumberIEI {
        number = number == null ? "" : number;
    }

    public static HlrNumberIEI decode(byte[] data) {
        if (data == null || data.length == 0) {
            return new HlrNumberIEI("");
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            sb.append(b & 0x0F);
        }
        return new HlrNumberIEI(sb.toString());
    }

    public static byte[] encode(HlrNumberIEI iei) {
        String number = iei.number();
        if (number == null || number.isEmpty()) {
            return new byte[0];
        }
        String digits = number.replaceAll("[^0-9]", "");
        byte[] result = new byte[digits.length()];
        for (int i = 0; i < digits.length(); i++) {
            result[i] = (byte) (digits.charAt(i) - '0');
        }
        return result;
    }

    public String toString() {
        return "HlrNumberIEI{number=" + number + "}";
    }
}