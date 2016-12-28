package org.gloria.algorithm.leetcode;

import org.junit.Test;

/**
 * Create on 2016/12/27 18:07.
 *
 * @author : gloria.
 * 
 * Implement atoi to convert a string to an integer.
 *
 * Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.
 *
 * Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.
 *
 */
public class StringToInteger {

    public int myAtoi(String str) {
        if (null == str || "".equals(str)) {
            return 0;
        }
        str = str.trim();
        boolean active = true;
        if (str.startsWith("-")) {
            str = str.substring(1);
            active = false;
        } else if (str.startsWith("+")) {
            str = str.substring(1);
        }

        long result = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                break;
            }
            result = 10 * result + (str.charAt(i) - '0');

            if (active) {
                if (result > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                }
            } else {
                if (-result < Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                }
            }
        }
        return active ? (int) result : -(int) result;
    }
    
    @Test
    public void test() {
        String str = "2147483648";
        System.out.println(myAtoi(str));
    }
    
}
