package org.gloria.algorithm.sort;

import java.util.Arrays;

/**
 * Create on 2016/12/19 15:28.
 *
 * @author : gloria.
 * @see : https://zh.wikipedia.org/wiki/%E5%A0%86%E6%8E%92%E5%BA%8F
 */
public class HeapSort {

    public static int left(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    public static int right(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    public static int parent(int childIndex) {
        return (childIndex - 1) / 2;
    }

    /**
     * 建立最大堆
     * @param array
     */
    public static void buildMaxHeap(Integer[] array) {

        for (int i = array.length / 2; i >= 0; i--) {
            maxHeapify(array, i, array.length);
            
        }
        
    }

    /**
     * 堆调整
     * @param array
     * @param i
     * @param length
     */
    public static void maxHeapify(Integer[] array, int i, int length) {
        int left = left(i);
        int right = right(i);
        int max = i;

        if (left < length && array[left] > array[i]) {
            max = left;
        }

        if (right < length && array[right] > array[max]) {
            max = right;
        }

        if (max != i) {
            int temp = array[i];
            array[i] = array[max];
            array[max] = temp;
            maxHeapify(array, max, length);
        }
        
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{16, 4, 10, 14, 7, 9, 3, 2, 8, 1};
        buildMaxHeap(array);
        Arrays.asList(array).forEach(integer -> System.out.print(integer + ","));

        System.out.println("");
        for (int i = 0; i < array.length; i++) {
            int temp = array[0];
            array[0] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
            maxHeapify(array, 0,array.length - i - 1);
        }
        
        Arrays.asList(array).forEach(integer -> System.out.print(integer + ","));
        
    }
}
