package com.nt.binary.Tree;

/**
 * @author Enzo
 * @date : 2024/2/22
 */
public class BalanceBinaryTree {
    /**
     * 方法一
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {

        if (root == null) return true;

        return Math.abs(height(root.left) - height(root.right)) <= 1
                && isBalanced(root.left) && isBalanced(root.right);
    }

    // 定义一个计算数高度的方法
    public int height(TreeNode root) {
        if (root == null) return 0;
        return Math.max(height(root.left), height(root.right)) + 1;
    }


    /**
     * 后序遍历
     * @param root
     * @return
     */
    public Boolean isBalanced1(TreeNode root) {

        if (root == null) return null;

        return balancedHeight(root) > -1;
    }


    public int balancedHeight(TreeNode root){

        if (root == null) return 0;
        // 递归计算作左右子树高度
        int leftHeight = balancedHeight(root.left);
        int rightHeight = balancedHeight(root.right);
        // 如果子树不平衡 直接返回-1
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight ) > 1){
            return -1;
        }
        // 如果平衡 返回当前高度
        return Math.max(leftHeight, rightHeight) + 1;
    }


    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);

        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;

        BalanceBinaryTree balancedBinaryTree = new BalanceBinaryTree  ();

        System.out.println(balancedBinaryTree.isBalanced(node1));
    }
}
