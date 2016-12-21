package org.gloria.algorithm.leetcode;

import org.junit.Test;

import java.util.Stack;


/**
 * Create on 2016/12/20 16:11.
 *
 * @author : gloria.
 * @see : https://leetcode.com/problems/add-two-numbers/
 * You are given two linked lists representing two non-negative numbers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

 * Example:

 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 */
public class AddTwoNumbersII {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode> stack1 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1);
            l1 = l1.next;
        }

        Stack<ListNode> stack2 = new Stack<>();
        while (l2 != null) {
            stack2.push(l2);
            l2 = l2.next;
        }

        int remainder = 0;
        ListNode node = new ListNode(0);
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            int sum = 0;
            if (!stack1.isEmpty()) {
                sum += stack1.pop().val;
            }
            if (!stack2.isEmpty()) {
                sum += stack2.pop().val;
            }
            sum += remainder;
            node.val = sum % 10;
            remainder = sum / 10;
            ListNode head = new ListNode(remainder);
            head.next = node;
            node = head;
        }

        return node.val == 0 ? node.next : node;

    }



    public class ListNode {
        int val;

        ListNode next;

        ListNode(int x) {
            
            val = x;
        }
    }

    @Test
    public void test() {
        ListNode l1 = new ListNode(7);
        ListNode l11 = new ListNode(2);
        ListNode l12 = new ListNode(4);
        ListNode l13 = new ListNode(3);
        l1.next = l11;
        l11.next = l12;
        l12.next = l13;

        ListNode l2 = new ListNode(5);
        ListNode l21 = new ListNode(6);
        ListNode l22 = new ListNode(4);
        l2.next = l21;
        l21.next = l22;
        ListNode node = addTwoNumbers(l1, l2);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }
  
}
