package me.samuelh2005.java_mobile.libosmocom.primitive;

public final class BcdUtil {
    private BcdUtil() {}

    public static String decodeDigits(byte[] data, int offset) {
        if (data == null || offset < 0 || offset >= data.length) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = offset; i < data.length; i++) {
            int low = data[i] & 0x0F;
            int high = (data[i] >> 4) & 0x0F;

            if (low <= 9) {
                sb.append(low);
            }
            if (high != 0x0F && high <= 9) {
                sb.append(high);
            }
        }
        return sb.toString();
    }

    public static byte[] encodeDigits(String value) {
        String digits = normalizeDigits(value);
        if (digits.isEmpty()) {
            return new byte[0];
        }

        int len = (digits.length() + 1) / 2;
        byte[] result = new byte[len];

        for (int i = 0; i < digits.length(); i++) {
            int digit = digits.charAt(i) - '0';
            if ((i & 1) == 0) {
                result[i / 2] = (byte) digit;
            } else {
                result[i / 2] |= (byte) (digit << 4);
            }
        }

        if ((digits.length() & 1) == 1) {
            result[len - 1] |= (byte) 0xF0;
        }

        return result;
    }

    public static byte[] encodeDigitsWithPrefix(int prefix, String value) {
        byte[] digits = encodeDigits(value);
        if (digits.length == 0 && normalizeDigits(value).isEmpty()) {
            return new byte[0];
        }

        byte[] result = new byte[digits.length + 1];
        result[0] = (byte) prefix;
        System.arraycopy(digits, 0, result, 1, digits.length);
        return result;
    }

    private static String normalizeDigits(String value) {
        if (value == null || value.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder(value.length());
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (c >= '0' && c <= '9') {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
