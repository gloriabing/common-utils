package org.gloria.algorithm.leetcode;

import org.junit.Test;

/**
 * Create on 2016/12/21 15:22.
 *
 * @author : gloria.
 * 
 * @see : https://leetcode.com/problems/divide-two-integers/
 */
public class DivideTwoIntegers {

    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }
        if (dividend == 0) {
            return 0;
        }
        long absDividend = Math.abs((long) dividend);

        long absDivisor = Math.abs((long) divisor);

        long tempDivisor = absDivisor;
        int length = 0;
        while (tempDivisor != 0) {
            tempDivisor >>= 1;
            length++;
        }

        long result = 0;
        for (int i = 32 - length; i >= 0; i--) {
            if ((absDivisor << i) <= absDividend) {
                result += (1l << i);
                absDividend -= (absDivisor << i);
            }
        }

        if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) {
            return (int)-result;
        }

        if (result > Integer.MAX_VALUE) {
            result = -1 * ((int) result + 1);
        }
        return (int)result;

    }

//    public int divide(int dividend, int divisor) {
//        if (divisor == 0) {
//            return Integer.MAX_VALUE;
//        }
//        if (dividend == 0) {
//            return 0;
//        }
//        int absDividend = Math.abs(dividend);
//        if (dividend == Integer.MIN_VALUE) {
//            absDividend = -(dividend + 1);
//        }
//        int absDivisor = Math.abs(divisor);
//
//        int value = 0;
//
//        while (absDividend >= 0) {
//            absDividend -= absDivisor;
//            if (absDividend >= 0) {
//                value++;
//            }
//        }
//        if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) {
//            return -value;
//        } 
//
//        return value;
//    }

    
    @Test
    public void test() {
//        System.out.println(divide(-2147483648, -1));
        System.out.println(divide(2147483647, -1));
//        System.out.println(divide(-14, 3));
    }
    
}
