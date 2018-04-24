package com.liu.j2setest.mianshiTest;

/**
 * Created by liuzhilei on 2018/4/24.
 * 以k为一组反转单链表，最后不足K个节点的也反转
 */
public class Test5 {

    class Node {
        Node next;
        Integer value;
    }

    public static void main(String[] args) {
        Node node = new Test5().new Node();
        node.value = 5;

        node.next = new Test5().new Node();
        node.next.value = 6;

        node.next.next = new Test5().new Node();
        node.next.next.value = 3;

        node.next.next.next = new Test5().new Node();
        node.next.next.next.value = 7;

        node.next.next.next.next = new Test5().new Node();
        node.next.next.next.next.value = 2;

        node.next.next.next.next.next = new Test5().new Node();
        node.next.next.next.next.next.value = 8;

        node.next.next.next.next.next.next = new Test5().new Node();
        node.next.next.next.next.next.next.value = 1;

        Node node1 = reverseNode(node, 3);
        System.out.println(node1);
    }

    public static Node reverseNode(Node node, int k) {
        Node preNode = null;//记录当前节点的上一个节点,这个节点是反转后的下一节点
        Node cur = node;
        int count = 0;
        while (count < k && cur != null) {
            Node a = cur.next;
            cur.next = preNode;
            preNode = cur; //当前节点赋给preNode，用于下次循环将比指向操作节点的next,b循环到最后，就是反转节点的头节点
            cur = a;
            count++;
        }

        //分组执行，每次走到这就是走完了一个K组,把本组的最后一个值的next指向下一组转换后的第一个值，就联合成一条链表了
        if (cur != null) {
            node.next = reverseNode(cur, 3);
        }

        return preNode;
    }


}
