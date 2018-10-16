package com.liu.j2setest.mianshiTest.binarytree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by liuzhilei3 on 2018/10/16.
 * 对二叉树的操作
 * 这里的操作都是正确的
 */
public class BinaryTreeOperate {
    private TreeNode root;

    public BinaryTreeOperate(TreeNode root) {
        this.root = root;
    }

    /**
     * 前序遍历
     */
    public void preLeft(TreeNode node) {
        if (node != null) {
            System.out.println(node.getValue());
            preLeft(node.getLeft());
            preLeft(node.getRight());
        }
    }

    /**
     * 中序遍历
     */
    public void preMiddle(TreeNode node) {
        if (node != null) {
            preMiddle(node.getLeft());
            System.out.println(node.getValue());
            preMiddle(node.getRight());
        }
    }

    /**
     * 后序遍历
     */
    public void preRight(TreeNode node) {
        if (node != null) {
            preRight(node.getLeft());
            preRight(node.getRight());
            System.out.println(node.getValue());
        }
    }

    /**
     * 层序遍历
     */
    public void preLevel(TreeNode root) {
        Queue<TreeNode> list = new LinkedList<>();
        list.add(root);
        while (!list.isEmpty()) {
            TreeNode node = list.poll();
            System.out.println(node.getValue());
            if (node.getLeft() != null) list.add(node.getLeft());
            if (node.getRight() != null) list.add(node.getRight());
        }
    }

    /**
     * 节点个数
     */
    public int getTreeCount(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = getTreeCount(node.getLeft());
        int right = getTreeCount(node.getRight());
        return 1 + left + right;
    }

    /**
     * 数的深度
     */
    public int getDepth(TreeNode node) {
        int depth = 0;
        if (node != null) {
            int leftDepth = getDepth(node.getLeft());
            int rightDepth = getDepth(node.getRight());
            depth = 1 + (leftDepth > rightDepth ? leftDepth : rightDepth);
        }
        return depth;
    }


}
