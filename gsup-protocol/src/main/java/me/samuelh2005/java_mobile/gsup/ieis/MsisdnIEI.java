package me.samuelh2005.java_mobile.gsup.ieis;

public record MsisdnIEI(String value) {
    public MsisdnIEI {
        value = value == null ? "" : value;
    }

    public static MsisdnIEI decode(byte[] data) {
        if (data == null || data.length < 2) {
            return new MsisdnIEI("");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < data.length; i++) {
            int nibbleLow = data[i] & 0x0F;
            int nibbleHigh = (data[i] >> 4) & 0x0F;
            if (nibbleHigh != 0x0F && nibbleHigh <= 9) {
                sb.append(nibbleHigh);
            }
            if (nibbleLow != 0x0F && nibbleLow <= 9) {
                sb.append(nibbleLow);
            }
        }
        return new MsisdnIEI(sb.toString());
    }

    public static byte[] encode(MsisdnIEI iei) {
        if (iei.value == null || iei.value.isEmpty()) {
            return new byte[0];
        }
        String digits = iei.value.replaceAll("[^0-9]", "");
        int odd = digits.length() % 2;
        int len = (digits.length() + 1) / 2;
        byte[] result = new byte[1 + len];
        result[0] = (byte) 0x91;
        for (int i = 0; i < digits.length(); i++) {
            int digit = digits.charAt(i) - '0';
            if (i % 2 == 0) {
                result[1 + i / 2] = (byte) digit;
            } else {
                result[1 + i / 2] |= (byte) (digit << 4);
            }
        }
        if (odd == 1) {
            result[1 + len - 1] |= (byte) 0xF0;
        }
        return result;
    }

    public String toString() {
        return "MsisdnIEI{msisdn=" + value + "}";
    }
}