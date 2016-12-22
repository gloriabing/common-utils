package org.gloria.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * Create on 2016/12/19 11:30.
 *
 * @author : gloria.
 */
public class SortTest {
    Integer[] array = new Integer[]{3, 7, 1, 2, 0, 8, 3};
    
    @Test
    public void test() {
        
        SelectionSort.sort(array);
        Arrays.asList(array).forEach(i -> System.out.print(i + ","));

//        System.out.println();
//
//        Object[] objects = new Object[]{"abc", "abf", "123", "ggg", "qwe", "qwr","&*("};
//        SelectionSort.sort(objects);
//        Arrays.asList(objects).forEach((o) -> {
//            System.out.print(o + ", ");
//        });
    }
//0,1,2,3,3,7,8
    @Test
    public void testQuickSort() {
//        QuickSort.quickSort(array, 0, array.length - 1);
//        Arrays.asList(array).forEach(integer -> System.out.print(integer + ","));

        QuickSort.sort(array, 0, array.length - 1);

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ".");
        }
    }
}
