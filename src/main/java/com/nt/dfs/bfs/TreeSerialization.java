package com.nt.dfs.bfs;


import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author Enzo
 * @date : 2024/3/5
 */
public class TreeSerialization {


    /**
     * 序列化方法
     */
    public String serialize(TreeNode root) {
        // 定义一个StringBuffer
        StringBuffer result = new StringBuffer();
        result.append("[");

        dfs_serialize(root, result);

        result.deleteCharAt(result.length() - 1);
        result.append("]");
        return result.toString();
    }

    /**
     * 实现深度优先搜索
     *
     * @param root
     * @param result
     */
    private void dfs_serialize(TreeNode root, StringBuffer result) {

        // 基准情况
        if (root == null) {
            result.append("null,");
            return;
        }
        // 将当前根节点的值序列化添加到result
        result.append(root.val + ",");
        // 递归处理左右子树
        dfs_serialize(root.left, result);
        dfs_serialize(root.right, result);
    }

    /**
     * 反序列化
     *
     * @param data
     * @return
     */
    public TreeNode deserialize(String data) {

        String[] dataArr = data.split(",");
        LinkedList<String> dataList = new LinkedList<>(Arrays.asList(dataArr));
        // 删除方括号
        String firstElement = dataList.getFirst().substring(1);
        dataList.removeFirst();
        dataList.addLast(firstElement);
        String lastElement = dataList.getLast().substring(0, dataList.getLast().length() - 1);
        dataList.removeLast();
        dataList.addLast(lastElement);
        return dfs_deserialize(dataList);
    }


    /**
     * 实现递归方法 辅助反序列化
     *
     * @param dataList
     * @return
     */

    private TreeNode dfs_deserialize(LinkedList<String> dataList) {
        if (dataList.getFirst().equals("null")) {
            dataList.removeFirst();
            return null;
        }
        // 获取当前节点
        TreeNode node = new TreeNode(Integer.valueOf(dataList.getFirst()));

        // 处理完就删除
        dataList.removeFirst();
        // 递归调用 定义当前节点的左右子几点
        node.left = dfs_deserialize(dataList);
        node.right = dfs_deserialize(dataList);

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

        TreeSerialization treeSerialization = new TreeSerialization();
        System.out.println(treeSerialization.serialize(node1));

        TreeNode newTree = treeSerialization.deserialize(treeSerialization.serialize(node1));
    }

}
