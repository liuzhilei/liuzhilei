package com.liu.j2setest.mianshiTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhilei3 on 2018/4/26.
 * 以k为一组反转单链表，最后不足K个节点的也反转
 */
public class Test6 {
    class Node {
        Test6.Node next;
        Integer value;
    }

    public static void main(String[] args) {


        Test6.Node node = new Test6().new Node();
        node.value = 5;

        node.next = new Test6().new Node();
        node.next.value = 6;

        node.next.next = new Test6().new Node();
        node.next.next.value = 3;

        node.next.next.next = new Test6().new Node();
        node.next.next.next.value = 7;

        node.next.next.next.next = new Test6().new Node();
        node.next.next.next.next.value = 2;

        node.next.next.next.next.next = new Test6().new Node();
        node.next.next.next.next.next.value = 8;

        node.next.next.next.next.next.next = new Test6().new Node();
        node.next.next.next.next.next.next.value = 1;

        node.next.next.next.next.next.next.next = new Test6().new Node();
        node.next.next.next.next.next.next.next.value = 4;

        Test6.Node node1 = reverseNode(node, 3);
        System.out.println(node1);

    }

    private static Node reverseNode(Node node, int k) {
        Node preNode = null;
        Node cur = node;               
        int count = 0;
        //剩余节点大于等于k，就继续反转
        if (getSize(cur) >= k) {
            while (count < k && cur != null) {
                Node nextNode = cur.next;
                cur.next = preNode;
                preNode = cur;
                cur = nextNode;
                count++;
            }

            if (cur != null) {
                node.next = reverseNode(cur, k);
            }

            return preNode;
        }


        return cur;
    }

    /**
     * 统计当前节点之后的剩余节点数量
     *
     * @param node
     * @return
     */
    private static int getSize(Node node) {
        int count = 0;
        node = node.next;
        while (node != null) {
            count++;
            node = node.next;
        }
        return count;
    }

}
