package org.gloria.algorithm.leetcode;

import org.junit.Test;

/**
 * Create on 2016/12/28 14:51.
 *
 * @author : gloria.
 * 
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a number of rows:

 * string convert(string text, int nRows);
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 * 
 */
public class ZigZagConversion {

    public String convert(String s, int numRows) {
        if (numRows <= 1) {
            return s;
        }

        StringBuffer[] array = new StringBuffer[numRows];
        for (int i = 0; i < numRows; i++) {
            array[i] = new StringBuffer("");
        }

        int index = 0;
        int increment = 1;
        for (int i = 0; i < s.length(); i++) {
            array[index].append(s.charAt(i));
            if (index == numRows - 1) {
                increment = -1;
            }
            if (index == 0) {
                increment = 1;
            }
            index += increment;
        }
        for (int i = 1; i < array.length; i++) {
            array[0].append(array[i].toString());
        }
        return array[0].toString();
    }
    
    @Test
    public void test() {
        System.out.println(convert("AB", 1));
    }
}
