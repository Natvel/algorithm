package com.nt.binary.Tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Enzo
 * @date : 2024/9/4
 */
public class BuildTree {
    Map<Integer, Integer> map = new HashMap();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // 做映射 将中序遍历的结果及对应的下标映射
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        TreeNode treeNode = recurBuild(postorder, 0, inorder.length - 1, 0, postorder.length - 1);
        return treeNode;
    }

    public TreeNode recurBuild(int[] postorder, int is, int ie, int ps, int pe) {
        if (is > ie || ps > pe) {
            return null;
        }
        // 找到树根节点
        TreeNode root = new TreeNode(postorder[pe]);
        // 从中序遍历结果中拿到根节点的下标
        Integer ri = map.get(postorder[pe]);
        root.left = recurBuild(postorder, is, ri - 1, ps, ps + ri - 1);
        root.right = recurBuild(postorder, ri + 1, ie, ps + ri - is, pe - 1);
        return root;
    }

}
