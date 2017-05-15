package com.liu.j2setest.java数据结构和算法.二叉树;

/**
 * Created by liuzhilei on 2017/5/15.
 * 表示树本身的类
 */
public class Tree {
    private Node root;

    public Node find(int key) {
        Node current = root;
        while (current.iData != key) {
            if (key < current.iData) {
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
            if (current == null) {
                return null;
            }
        }

        return current;
    }

    public void insert(int id, double dd) {
        Node newNode = new Node();
        newNode.iData = id;
        newNode.dData = dd;

        if (root == null) {
            root = newNode;
        } else {
            Node current = root;
            Node parent;
            while (true) {
                parent = current;
                if (id < current.iData) {
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = newNode;
                        break;
                    }
                }
                if (id > current.iData) {
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = newNode;
                        break;
                    }
                }


            }
        }

    }

    /**
     * 最小值
     *
     * @return
     */
    public Node minNum() {
        Node current, last = null;
        current = root;
        while (current != null) {
            last = current;
            current = current.leftChild;
        }
        return last;
    }

    /**
     * 最大值
     * @return
     */
    public Node maxNum() {
        Node current, last = null;
        current = root;
        while (current != null) {
            last = current;
            current = current.rightChild;
        }
        return last;
    }


    public void delete(int id) {

    }

}
