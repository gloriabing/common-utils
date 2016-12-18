package org.gloria.algorithm.sort;

import java.util.Arrays;

/**
 * Create on 2016/12/17 18:33.
 *
 * @author : gloria.
 * @see : https://zh.wikipedia.org/wiki/%E9%80%89%E6%8B%A9%E6%8E%92%E5%BA%8F
 */
public class SelectionSort {

    public static void sort(Object[] objects) {
        int cursor;
        for (int i = 0; i < objects.length; i++) {
            cursor = i;
            for (int j = i; j < objects.length; j++) {
                if (String.valueOf(objects[cursor]).compareTo(String.valueOf(objects[j])) > 0) {
                    cursor = j;
                }
            }
            if (cursor != i) {
                Object temp = objects[i];
                objects[i] = objects[cursor];
                objects[cursor] = temp;
            }
        }

    }

    public static void sort(Integer[] array) {
        int cursor;
        for (int i = 0; i < array.length; i++) {
            cursor = i;
            for (int j = i; j < array.length; j++) {
                if (array[cursor] > array[j]) {
                    cursor = j;
                }
            }
            if (cursor != i) {
                int temp = array[i];
                array[i] = array[cursor];
                array[cursor] = temp;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{3, 7, 1, 2, 0, 8, 3};
        SelectionSort.sort(array);
        Arrays.asList(array).forEach(i -> System.out.print(i + ","));

        System.out.println();

        Object[] objects = new Object[]{"abc", "abf", "123", "ggg", "qwe", "qwr","&*("};
        SelectionSort.sort(objects);
        Arrays.asList(objects).forEach((o) -> {
            System.out.print(o + ", ");
        });
    }
}
