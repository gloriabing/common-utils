package org.gloria.algorithm.string;

import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

/**
 * Create on 2016/12/19 18:03.
 *
 * @author : gloria.
 */
public class StringReverse {

    /**
     * 非递归字符串反转
     * @param oldStr
     * @return
     */
    public static String reverse(String oldStr) {

        char[] chars = oldStr.toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            char c = chars[i];
            chars[i] = chars[chars.length - i - 1];
            chars[chars.length - i - 1] = c;
        }
        
        return Arrays.toString(chars);
    }

    /**
     * 递归实现字符串反转
     * @param oldStr
     * @return
     */
    public static String recursiveReverse(String oldStr) {
        if (StringUtils.isBlank(oldStr)) {
            return "";
        }
        return recursiveReverse(oldStr.substring(1)) + oldStr.charAt((0));
    }

    public static void main(String[] args) {
        String str = "abcdefg";
        System.out.println(recursiveReverse(str));
    }
    
}
