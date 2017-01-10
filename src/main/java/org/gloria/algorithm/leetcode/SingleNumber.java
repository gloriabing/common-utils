package org.gloria.algorithm.leetcode;

import org.junit.Test;

/**
 * Create on 2017/1/10 11:26.
 *
 * @author : gloria.
 *
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * 
 */
public class SingleNumber {

    public int singleNumber(int[] nums) {
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }
    
    @Test
    public void test() {
        int[] nums = new int[]{1,3,4,5,5,1,3};
        System.out.println(singleNumber(nums));
    }
}
