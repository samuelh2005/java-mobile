package me.samuelh2005.java_mobile.gsup.ieis;

public record NumVectorsReqIEI(int numVectors) {
    public static NumVectorsReqIEI decode(byte[] data) {
        if (data == null || data.length < 1) {
            return new NumVectorsReqIEI(1);
        }
        return new NumVectorsReqIEI(data[0] & 0xFF);
    }

    public static byte[] encode(NumVectorsReqIEI iei) {
        return new byte[] {(byte) iei.numVectors()};
    }

    public String toString() {
        return "NumVectorsReqIEI{numVectors=" + numVectors + "}";
    }
}