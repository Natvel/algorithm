package com.nt.binary.Tree;

import com.google.common.collect.Lists;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Enzo
 * @date : 2024/2/22
 */
public class ValidateBST {

    /**
     * 先序遍历
     * @param root
     * @return
     */
    public Boolean isValidBST(TreeNode root){
        if (root == null) return true;

        return validator(root,null,null);
    }

    List<Integer> inOrderArray = Lists.newArrayList();

    /**
     * 中序遍历
     * @param root
     * @return
     */
    public Boolean isValidBST1(TreeNode root){
        // 1.中序遍历 得到升序数组
        inOrder(root);
        for (int i = 0; i < inOrderArray.size(); i++) {
            if (i > 0 && inOrderArray.get(i) <= inOrderArray.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    private void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        inOrderArray.add(root.val);
        inOrder(root.right);
    }

    /**
     * 定义一个辅助校验器 用来传入上下界递归调用
     * @param root
     * @param lowerBound
     * @param upperBound
     * @return
     */
    public Boolean validator(TreeNode root,Integer lowerBound,Integer upperBound){
        if (root == null) return true;
        // 1. 判断当前节点的值是否在上下界范围内 如果超出直接返回false
        if (lowerBound != null && root.val <= lowerBound){
            return false;
        }
        if (upperBound != null && root.val >= upperBound){
            return false;
        }
        // 2.递归判读左右子树
        return validator(root.left, lowerBound, root.val) && validator(root.right, root.val, upperBound);
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(6);
        TreeNode node5 = new TreeNode(8);

        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;

        ValidateBST validateBST = new ValidateBST();

        System.out.println(validateBST.isValidBST(node1));
    }
}
