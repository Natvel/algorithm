package com.nt.binary.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Enzo
 * @date : 2024/9/2
 */
public class MaxDepth {
    public int maxDepth(TreeNode root) {
        int dept = 0;
        if (root == null){
            return dept;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null){
                    queue.offer(poll.left);
                }
                if (poll.right != null){
                    queue.offer(poll.right);
                }
            }
            dept++;
        }
        return dept;
    }
}
