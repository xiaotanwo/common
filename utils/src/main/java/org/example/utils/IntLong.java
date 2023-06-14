package org.example.utils;

public class IntLong {

    public static TwoInt longToTwoInt(long value) {
        long highValue = (value >>> Integer.SIZE) & 0xffffffffL;
        long lowValue = value & 0xffffffffL;
        return new TwoInt((int) highValue, (int) lowValue);
    }

    public static long twoIntToLong(int highValue, int lowValue) {
        return twoIntToLong0(highValue, lowValue);
    }

    public static long twoIntToLong(TwoInt towInt) {
        return twoIntToLong0(towInt.high, towInt.low);
    }

    private static long twoIntToLong0(int highValue, int lowValue) {
        long result = highValue;
        result <<= Integer.SIZE;
        result |= (lowValue & 0xffffffffL);
        return result;
    }

    public static class TwoInt {
        public final int high;
        public final int low;

        public TwoInt(int high, int low) {
            this.high = high;
            this.low = low;
        }
    }
}
