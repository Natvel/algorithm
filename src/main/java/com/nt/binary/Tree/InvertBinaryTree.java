package com.nt.binary.Tree;

/**
 * @author Enzo
 * @date : 2024/2/22
 */

public class InvertBinaryTree {

    /**
     * 先序遍历
     *
     * @param node
     * @return
     */
    public TreeNode invertTree(TreeNode node) {

        // 处理基准场景
        if (node == null) return null;

        // 1.先处理根节点 交换左右子节点
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
        // 2. 递归处理右子树
        invertTree(node.left);
        invertTree(node.right);
        return node;
    }

    /**
     * 后 序遍历
     *
     * @param node
     * @return
     */
    public TreeNode invertTree1(TreeNode node) {
        // 1. 递归处理右子树
        TreeNode left = invertTree1(node.left);
        TreeNode right = invertTree1(node.right);

        node.left = right;
        node.right = left;
        return node;
    }


    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(9);
        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.left = node6;
        node3.right = node7;
        TreePrinter.printTreeLevelOrder(node1);


        System.out.println();

        InvertBinaryTree invertBinaryTree = new InvertBinaryTree();

        TreePrinter.printTreeLevelOrder(invertBinaryTree.invertTree(node1));
    }
}
