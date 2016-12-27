package org.gloria.algorithm.leetcode;


import org.junit.Test;

import java.util.Stack;

/**
 * Create on 2016/12/25 23:20.
 *
 * @author : gloria.
 * 
 * The count-and-say sequence is the sequence of integers beginning as follows:
 * 1, 11, 21, 1211, 111221, ...

 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * Given an integer n, generate the nth sequence.
 * 
 */
public class CountAndSay {

    public String countAndSay(int n) {
        String output = "";

        String input = "1";
        Stack<Character> stack = new Stack();
        for (int i = 1; i < n; i++) {
            int count = 1;
            for (int j = 0; j < input.length(); j++) {
                if (stack.empty()) {
                    stack.push(input.charAt(j));
                } else {
                    if (stack.peek() == input.charAt(j)) {
                        count++;
                    } else {
                        output += (count + String.valueOf(stack.pop().toString()));
                        stack.push(input.charAt(j));
                        count = 1;
                    }
                } 
            }
            if (!stack.empty()) {
                output += (count + String.valueOf(stack.pop().toString()));
            } 
            input = output;
            output = "";
        }
        
        return input;
    }

    public String countAndSay1(int n) {
        String output = "";

        String input = "1";
        for (int i = 1; i < n; i++) {
            int count = 1;
            int j;
            for (j = 0; j < input.length() - 1; j++) {
                if (input.charAt(j) == input.charAt(j + 1)) {
                    count++;
                } else {
                    output += (count + String.valueOf(input.charAt(j)));
                    count = 1;

                } 
            }
            if (input.charAt(input.length() - 1) == input.charAt(j)) {
                output += (count + String.valueOf(input.charAt(input.length() - 1)));
            }
           
            input = output;
            output = "";
        }

        return input;
    }
    
    @Test
    public void test() {
        System.out.println(countAndSay1(6));
        
    }
    
}
