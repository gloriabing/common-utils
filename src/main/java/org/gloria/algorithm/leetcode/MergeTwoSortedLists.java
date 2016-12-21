package org.gloria.algorithm.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * Create on 2016/12/21 11:08.
 *
 * @author : gloria.
 * 
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 */
public class MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (null == l1) {
            return l2;
        }
        if (null == l2) {
            return l1;
        }
        int[] array = new int[length(l1) + length(l2)];
        int index = 0;
        while (l1 != null) {
            array[index++] = l1.val;
            l1 = l1.next;
        }
        while (l2 != null) {
            array[index++] = l2.val;
            l2 = l2.next;
        }
        Arrays.sort(array);

        ListNode node = new ListNode(0);
        ListNode nextNode = node;
        for (int i = 0; i < array.length; i++) {
            nextNode.next = new ListNode(array[i]);
            nextNode = nextNode.next;
        }


        return node.val == 0 ? node.next : node;
    }

    public int length(ListNode l) {
        int length = 0;
        while (l != null) {
            length++;
            l = l.next;
        }
        return length;
    }

    /**
     * 速度更快
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoListsII(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode node = new ListNode(0);
        ListNode nextNode = node;
        while (l1 != null && l2 != null) {

            if (l1.val > l2.val) {
                nextNode.next = l2;
                nextNode = nextNode.next;
                l2 = l2.next;
            } else if (l2.val > l1.val) {
                nextNode.next = l1;
                nextNode = nextNode.next;
                l1 = l1.next;
            } else {
                nextNode.next = l1;
                nextNode = nextNode.next;
                l1 = l1.next;
                nextNode.next = l2;
                nextNode = nextNode.next;
                l2 = l2.next;
            }
        }
        if (l1 != null) {
            nextNode.next = l1;
        }
        if (l2 != null) {
            nextNode.next = l2;
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
        ListNode l1 = new ListNode(1);
        ListNode l11 = new ListNode(3);
        ListNode l12 = new ListNode(4);
//        ListNode l13 = new ListNode(4);
        l1.next = l11;
        l11.next = l12;
//        l12.next = l13;

        ListNode l2 = new ListNode(0);
//        ListNode l21 = new ListNode(4);
//        ListNode l22 = new ListNode(5);
//        l2.next = l21;
//        l21.next = l22;
        ListNode node = mergeTwoListsII(l1, l2);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }
}
