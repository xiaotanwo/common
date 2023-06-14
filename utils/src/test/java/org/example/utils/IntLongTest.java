package org.example.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class IntLongTest {

    @Test
    public void test() {
        // MAX_VALUE
        test(Integer.MAX_VALUE, Integer.MAX_VALUE);
        test(Integer.MAX_VALUE, Integer.MIN_VALUE);
        test(Integer.MAX_VALUE, 0);
        test(Integer.MAX_VALUE, 2313);
        test(Integer.MAX_VALUE, -5442);
        test(Integer.MAX_VALUE, -1);

        // MIN_VALUE
        test(Integer.MIN_VALUE, Integer.MAX_VALUE);
        test(Integer.MIN_VALUE, Integer.MIN_VALUE);
        test(Integer.MIN_VALUE, 0);
        test(Integer.MIN_VALUE, 2313);
        test(Integer.MIN_VALUE, -5442);
        test(Integer.MIN_VALUE, -1);

        // 0
        test(0, Integer.MAX_VALUE);
        test(0, Integer.MIN_VALUE);
        test(0, 0);
        test(0, 2313);
        test(0, -5442);
        test(0, -1);

        // 2313
        test(2313, Integer.MAX_VALUE);
        test(2313, Integer.MIN_VALUE);
        test(2313, 0);
        test(2313, 2313);
        test(2313, -5442);
        test(2313, -1);

        // -5442
        test(-5442, Integer.MAX_VALUE);
        test(-5442, Integer.MIN_VALUE);
        test(-5442, 0);
        test(-5442, 2313);
        test(-5442, -5442);
        test(-5442, -1);

        // -1
        test(-1, Integer.MAX_VALUE);
        test(-1, Integer.MIN_VALUE);
        test(-1, 0);
        test(-1, 2313);
        test(-1, -5442);
        test(-1, -1);
    }

    private void test(int high, int low) {
        long l = IntLong.twoIntToLong(high, low);
        IntLong.TwoInt result = IntLong.longToTwoInt(l);
        assertEquals(high, result.high);
        assertEquals(low, result.low);
    }
}
