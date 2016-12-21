package org.gloria.algorithm.leetcode;

import org.junit.Test;

/**
 * Create on 2016/12/21 10:33.
 *
 * @author : gloria.
 * 
 * Determine whether an integer is a palindrome. Do this without extra space.
 */
public class PalindromeNumber {

    public boolean isPalindrome(int x) {
        
        if (x < 0) {
            return false;
        }
        int n = x;
        long result = 0;
        while(x != 0) {
            result = 10 * result + x % 10;
            x /= 10;
        }
        if (String.valueOf(result).equals(String.valueOf(n))) {
            return true;
        }
        return false;
    }

    @Test
    public void test() {
        int x = 1410110141;
        System.out.println(isPalindrome(x));
        
    }
    
}
