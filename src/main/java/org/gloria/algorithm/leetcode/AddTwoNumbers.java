package org.gloria.algorithm.leetcode;

import org.junit.Test;


/**
 * Create on 2016/12/20 16:11.
 *
 * @author : gloria.
 * @see : https://leetcode.com/problems/add-two-numbers/
 * You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * <p>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 */
public class AddTwoNumbers {
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode node = new ListNode(0);
        ListNode nextNode = node;
        int remainder = 0;
        while (l1 != null || l2 != null) {

            int sum = 0;
            if (l1 != null) {
                sum += l1.val;
            }
            if (l2 != null) {
                sum += l2.val;
            }
            sum += remainder;
            int result = sum % 10;
            
            nextNode.next = new ListNode(result);
            nextNode = nextNode.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
            remainder = sum / 10;
            
        }

        if (remainder != 0) {
            nextNode.next = new ListNode(remainder);
        }
        return node.next;
        
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
        ListNode l1 = new ListNode(1);
        ListNode l11= new ListNode(9);
        l1.next = l11;
        
        ListNode l2 = new ListNode(9);

        ListNode node = addTwoNumbers(l1, l2);

        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }
  
}
