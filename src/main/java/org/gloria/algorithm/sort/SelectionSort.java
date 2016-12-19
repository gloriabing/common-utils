package org.gloria.algorithm.sort;

/**
 * Create on 2016/12/17 18:33.
 *
 * @author : gloria.
 * @see : https://zh.wikipedia.org/wiki/%E9%80%89%E6%8B%A9%E6%8E%92%E5%BA%8F
 */
public class SelectionSort {

    public static void sort(Integer[] array) {
        int min;
        for (int i = 0; i < array.length; i++) {
            min = i;
            for (int j = i; j < array.length; j++) {
                if (array[min] > array[j]) {
                    min = j;
                }
            }
            if (min != i) {
                int temp = array[i];
                array[i] = array[min];
                array[min] = temp;
            }
        }
    }

    public static <T> void sort(T[] objects) {
        int min;
        for (int i = 0; i < objects.length; i++) {
            min = i;
            for (int j = i; j < objects.length; j++) {
                if (String.valueOf(objects[min]).compareTo(String.valueOf(objects[j])) > 0) {
                    min = j;
                }
            }
            if (min != i) {
                T temp = objects[i];
                objects[i] = objects[min];
                objects[min] = temp;
            }
        }

    }

}
