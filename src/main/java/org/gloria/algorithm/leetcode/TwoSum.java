package org.gloria.algorithm.leetcode;

import org.junit.Test;


/**
 * Create on 2016/12/20 15:44.
 *
 * @author : gloria.
 * 
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.

 * You may assume that each input would have exactly one solution.

 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,

 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];

        for (int i = 0; i < nums.length - 1; i++) {
            
            int rest = target - nums[i];

            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == rest) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
            
        }
        return nums;
    }

    @Test
    public void test() {
        int[] array = new int[]{3,2,4};
        int[] result = twoSum(array, 6);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
        
    }
}
