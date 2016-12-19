package org.gloria.algorithm.search;

/**
 * Create on 2016/12/19 17:30.
 *
 * @author : gloria.
 * @see : https://zh.wikipedia.org/wiki/%E4%BA%8C%E5%88%86%E6%90%9C%E7%B4%A2%E7%AE%97%E6%B3%95
 */
public class BinarySearch {

    /**
     * 非递归
     * @param array
     * @param target
     * @return
     */
    public static int search(int[] array, int target) {
        
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int middle = (left + right) / 2;
            if (array[middle] > target) {
                right = middle - 1;
            } else if (array[middle] < target) {
                left = middle + 1;
            } else {
                return middle;
            }
            
        }
        
        return Integer.MIN_VALUE;
    }

    /**
     * 递归
     * @param array
     * @param left
     * @param right
     * @param target
     * @return
     */
    public static int search(int[] array, int left, int right, int target) {
        if (left > right) {
            return Integer.MIN_VALUE;
        }
        int middle = (left + right) / 2;
        if (array[middle] > target) {
            return search(array, left, middle - 1, target);
        }
        if (array[middle] < target) {
            return search(array, middle + 1, right, target);
        }
        return middle;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 7, 8, 9, 10, 14, 16};
        System.out.println(search(array, 0, array.length - 1, 16));
    }
    
}
