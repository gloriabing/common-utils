package org.gloria.algorithm.sort;

/**
 * Create on 2016/12/17 18:32.
 *
 * @author : gloria.
 * @see : https://zh.wikipedia.org/wiki/%E6%8F%92%E5%85%A5%E6%8E%92%E5%BA%8F
 */
public class InsertSort {

    public static void sort(Integer[] array) {
        int temp;
        for (int i = 1; i < array.length; i++) {

            temp = array[i];
            int j = i - 1;
            for (; j >= 0 && array[j] > temp; j--) {

                array[j + 1] = array[j];
            }
            array[j + 1] = temp;
        }
        
    }

    public static void sort1(Integer[] array) {

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }


    }

}
