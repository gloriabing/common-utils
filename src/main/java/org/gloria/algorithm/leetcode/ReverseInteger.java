package org.gloria.algorithm.leetcode;

import org.junit.Test;

/**
 * Create on 2016/12/20 17:55.
 *
 * Reverse digits of an integer.

 * Example1: x = 123, return 321
 * Example2: x = -123, return -321
 * 
 * @author : gloria.
 */
public class ReverseInteger {

    public int reverse(int x) {
        boolean active = true;
        if (x <= Integer.MIN_VALUE) {
            return 0;
        }
        if (x < 0) {
            x = -x;
            active = false;
        }
        
        char[] chars = String.valueOf(x).toCharArray();
        String str = "";

        for (int i = chars.length - 1; i >= 0; i--) {
            str += chars[i];
        }

        Long result = Long.parseLong(str);
        if (result >= Integer.MAX_VALUE) {
            return 0;
        }
        if (active) {
            return result.intValue();
        } else {
            return (-1) * result.intValue();
        } 
    }

    public int reverseInteger(int x) {
        boolean active = true;
        if (x <= Integer.MIN_VALUE) {
            return 0;
        }
        if (x < 0) {
            active = false;
            x = -x;
        }

        long result = 0;
        while(x != 0) {
            result = 10 * result + x % 10;
            x /= 10;
        }

        if (result >= Integer.MAX_VALUE) {
            return 0;
        }
        if (active) {
            return (int) result;
        }
        return (-1) * (int) result;
    }

    @Test
    public void test() {
        int x = -1234;
        System.out.println(reverseInteger(x));
        
    }
}
