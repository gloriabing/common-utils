package org.gloria.algorithm.tree;

import org.junit.Test;

/**
 * Create on 2016/12/20 14:43.
 *
 * @author : gloria.
 */
public class BinaryTree {
    
    class TreeNode {
        int key;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int key) {
            this.key = key;
        }
    }

    /**
     * 前序遍历
     * @param tree
     */
    public static void preOrderTrave(TreeNode tree) {
        if (tree == null) {
            return;
        }
        System.out.println(tree.key);
        preOrderTrave(tree.left);
        preOrderTrave(tree.right);
    }

    /**
     * 中序遍历
     * @param tree
     */
    public static void inOrderTrave(TreeNode tree) {
        if (tree == null) {
            return;
        }
        inOrderTrave(tree.left);
        System.out.println(tree.key);
        inOrderTrave(tree.right);
    }

    /**
     * 后序遍历
     * @param tree
     */
    public static void postOrderTrave(TreeNode tree) {
        if (tree == null) {
            return;
        }
        inOrderTrave(tree.left);
        inOrderTrave(tree.right);
        System.out.println(tree.key);
    }
    

    @Test
    public void test() {
        TreeNode treeNode1 = new TreeNode(6);
        TreeNode treeNode2 = new TreeNode(3);
        TreeNode treeNode3 = new TreeNode(2);
        TreeNode treeNode4 = new TreeNode(1);
        TreeNode treeNode5 = new TreeNode(7);
        TreeNode treeNode6 = new TreeNode(8);
        TreeNode treeNode7 = new TreeNode(9);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;

        treeNode2.left = treeNode4;

        treeNode3.left = treeNode6;
        treeNode3.right = treeNode5;

        treeNode5.left = treeNode7;

        inOrderTrave(treeNode1);
    }
}
