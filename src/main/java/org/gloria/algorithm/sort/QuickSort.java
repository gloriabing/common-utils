package org.gloria.algorithm.sort;

/**
 * Create on 2016/12/19 11:29.
 *
 * @author : gloria.
 * @see : https://zh.wikipedia.org/wiki/%E5%BF%AB%E9%80%9F%E6%8E%92%E5%BA%8F
 */
public class QuickSort {

    public static int sort(Integer[] array, int left, int right) {
        if (left < right) {
            
            int key = array[left];
            
            int low = left;
            int high = right;
            while (low < high) {
                while (low < high && array[high] > key) {
                    high--;
                }
                array[low++] = array[high];
                while (low < high && array[low] < key) {
                    low++;
                }
                array[high--] = array[low];
            }
            array[low] = key;
            sort(array, left, low - 1);
            sort(array, low + 1, right);
        }
        
        return Integer.MIN_VALUE;
    }

}
