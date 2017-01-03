package org.gloria.algorithm.leetcode;

import org.junit.Test;

/**
 * Create on 2017/1/3 14:18.
 *
 * @author : gloria.
 * 
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 *
 * Given two integers x and y, calculate the Hamming distance.
 *
 * Note:
 * 0 ≤ x, y < 231.
 *
 * Example:
 *
 * Input: x = 1, y = 4
 *
 * Output: 2
 *
 * Explanation:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 *
 * The above arrows point to positions where the corresponding bits are different.
 * 
 */
public class HammingDistance {

    public int hammingDistance(int x, int y) {

        StringBuffer buf1 = toBinary(x);
        StringBuffer buf2 = toBinary(y);
        
        int count = 0;
        for (int i = 0; i < buf1.length(); i++) {
            if (buf1.charAt(i) != buf2.charAt(i)) {
                count++;
            }
        }
        return count;
    }
    
    public StringBuffer toBinary(int num) {
        StringBuffer buffer = new StringBuffer();

        while (num != 0) {
            buffer.append(num % 2);
            num /= 2;
        }

        int length = buffer.length();
        for (int i = 0; i < 32 - length; i++) {
            buffer.append(0);
        }
        return buffer;
    }

    @Test
    public void test() {
        int x = 1;
        int y = 4;
        System.out.println(hammingDistance(x, y));
//        System.out.println(toBinary(y));
        
    }
    
}
