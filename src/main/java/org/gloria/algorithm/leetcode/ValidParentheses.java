package org.gloria.algorithm.leetcode;

import org.junit.Test;

import java.util.Stack;

/**
 * Create on 2017/1/3 11:37.
 *
 * @author : gloria.
 */
public class ValidParentheses {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();


        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty()) {
                stack.push(match(s.charAt(i)));
            } else {
                char c = stack.peek();
                if (c == s.charAt(i)) {
                    stack.pop();
                } else {
                    stack.push(match(s.charAt(i)));
                } 
            } 
        }
        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }

    public char match(char c) {
        switch (c) {
            case '{':
                return '}';
            case '(':
                return ')';
            case '[':
                return ']';
            default:
                return '*';
        }
    }
    
    @Test
    public void test() {
        String str = "(";
        System.out.println(isValid(str));
    }
}
