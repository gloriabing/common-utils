package org.gloria.algorithm.leetcode;

import org.junit.Test;


/**
 * Create on 2016/12/22 14:26.
 *
 * @author : gloria.
 * 
 * Given two numbers represented as strings, return multiplication of the numbers as a string.
 */
public class MultiplyStrings {

    public String multiply(String num1, String num2) {

        int[] array = new int[num1.length() + num2.length()];

        for (int i = num2.length() - 1; i >= 0; i--) {
            for (int j = num1.length() - 1; j >= 0; j--) {
                int n = (num2.charAt(i) - '0') * (num1.charAt(j) - '0');
                n += array[i + j + 1];
                int carry = n / 10;
                int result = n % 10;
                array[i + j] += carry;
                
                array[i + j + 1] = result;
            }
        }
        int i = 0;
        for (; i < array.length; i++) {
            if (array[i] != 0) {
                break;
            }
        }
        StringBuffer resultBuf = new StringBuffer();
        for (; i < array.length; i++) {
            resultBuf.append(array[i]);
        }
        return resultBuf.toString().length() == 0 ? "0" : resultBuf.toString();
    }

    @Test
    public void test() {
        String num1 = "0";
        String num2 = "0";
        System.out.println(multiply(num1, num2));
    }
}
