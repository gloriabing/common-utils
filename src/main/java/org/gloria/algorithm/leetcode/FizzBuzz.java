package org.gloria.algorithm.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Create on 2017/1/5 10:40.
 *
 * @author : gloria.
 */
public class FizzBuzz {

    public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            list.add(match(i));
        }
        return list;
    }


    public String match(int n) {
        if (n % 3 == 0 && n % 5 == 0) {
            return "FizzBuzz";
        }
        if (n % 3 == 0) {
            return "Fizz";
        }

        if (n % 5 == 0) {
            return "Buzz";
        }

        return String.valueOf(n);
    }


    @Test
    public void test() {
        int n = 1;
        List<String> list = fizzBuzz(n);
        for (String s : list) {
            System.out.println(s);
        }
    }
        
}
