package me.samuelh2005.java_mobile.gsup.ieis;

public record HlrNumberIEI(String number) {
    public HlrNumberIEI {
        number = number == null ? "" : number;
    }

    public static HlrNumberIEI decode(byte[] data) {
        if (data == null || data.length < 1) {
            return new HlrNumberIEI("");
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            int nibbleHigh = (b >> 4) & 0x0F;
            int nibbleLow = b & 0x0F;
            if (nibbleHigh != 0x0F && nibbleHigh <= 9) {
                sb.append(nibbleHigh);
            }
            if (nibbleLow != 0x0F && nibbleLow <= 9) {
                sb.append(nibbleLow);
            }
        }
        return new HlrNumberIEI(sb.toString());
    }

    public static byte[] encode(HlrNumberIEI iei) {
        String number = iei.number();
        if (number == null || number.isEmpty()) {
            return new byte[0];
        }
        String digits = number.replaceAll("[^0-9]", "");
        int odd = digits.length() % 2;
        int len = (digits.length() + 1) / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < digits.length(); i++) {
            int digit = digits.charAt(i) - '0';
            if (i % 2 == 0) {
                result[i / 2] = (byte) (digit << 4);
            } else {
                result[i / 2] |= (byte) digit;
            }
        }
        if (odd == 1) {
            result[len - 1] |= (byte) 0x0F;
        }
        return result;
    }

    public String toString() {
        return "HlrNumberIEI{number=" + number + "}";
    }
}