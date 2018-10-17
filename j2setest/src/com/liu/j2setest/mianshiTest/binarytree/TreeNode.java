package com.liu.j2setest.mianshiTest.binarytree;

import java.util.logging.Level;

/**
 * Created by liuzhilei3 on 2018/10/16.
 * 定义二叉树
 */
public class TreeNode {
    private int value;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int value){
        this(value, null,null);
    }

    public TreeNode(int value,TreeNode left,TreeNode right){
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
