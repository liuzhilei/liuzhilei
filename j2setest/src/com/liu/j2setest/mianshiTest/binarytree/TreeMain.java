package com.liu.j2setest.mianshiTest.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhilei3 on 2018/10/16.
 */
public class TreeMain {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        TreeNode binaryTree = createBinaryTree(arr, 0);
        BinaryTreeOperate operate = new BinaryTreeOperate(binaryTree);
        //System.out.println(operate.getDepth(binaryTree));
        //operate.preLeft(binaryTree);
        operate.preMiddle(binaryTree);
        //operate.preRight(binaryTree);

        TreeNode node = init();
        BinaryTreeOperate o = new BinaryTreeOperate(node);
        //o.preLeft(node);
        //o.preMiddle(node);
        //o.preRight(node);node
        //System.out.println(o.getDepth(node));
        //System.out.println(o.getTreeCount(node));
        //o.preLevel(node);
    }

    public static TreeNode init() {//注意必须逆序建立，先建立子节点，再逆序往上建立，因为非叶子结点会使用到下面的节点，而初始化是按顺序初始化的，不逆序建立会报错
        TreeNode J = new TreeNode(8, null, null);
        TreeNode H = new TreeNode(4, null, null);
        TreeNode G = new TreeNode(2, null, null);
        TreeNode F = new TreeNode(7, null, J);
        TreeNode E = new TreeNode(5, H, null);
        TreeNode D = new TreeNode(1, null, G);
        TreeNode C = new TreeNode(9, F, null);
        TreeNode B = new TreeNode(3, D, E);
        TreeNode A = new TreeNode(6, B, C);
        return A;   //返回根节点
    }

    /**
     * 注意，这只是实现一个简单的二叉树，并不是平衡二叉树
     */
    public static TreeNode createBinaryTree(int[] arr, int index) {
        TreeNode node = new TreeNode(arr[index]);
        if (index < arr.length / 2) {
            node = new TreeNode(arr[index]);
            node.setLeft(createBinaryTree(arr, 2 * index + 1));
            node.setRight(createBinaryTree(arr, 2 * index + 2));
        }
        return node;
    }
}
