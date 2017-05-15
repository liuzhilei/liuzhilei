package com.liu.j2setest.java数据结构和算法.二叉树;

/**
 * Created by liuzhilei on 2017/5/15.
 * 操作树的类
 */
public class TreeApp {
    public static void main(String[] args) {
        Tree tree = new Tree();

        tree.insert(50, 1.5);
        tree.insert(25, 1.7);
        tree.insert(75, 1.9);

        Node node = tree.find(25);
        if (node != null) {
            System.out.println("发现了25节点");
        } else {
            System.out.println("没发现25节点");
        }


    }
}
