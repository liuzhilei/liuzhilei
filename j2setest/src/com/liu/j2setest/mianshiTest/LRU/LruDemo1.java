package com.liu.j2setest.mianshiTest.LRU;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuzhilei on 2018/4/18.
 * 利用链表和hashmap实现lru：最近最少访问
 * <p/>
 * 思路：双向链表和hashMap
 * 1.当需要插入新的数据项的时候，如果链表中存在该节点，就把该节点移动到链表头部；如果不存在，就在表头插入该节点
 * 2.当访问数据的时候，如果链表中存在，就把该节点放到表头，不存在则返回-1
 * 经过以上操作，就形成了最近最少访问的位于表尾位置
 */
public class LruDemo1<K, V> {

    class Node {
        Node pre;
        Node next;
        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node head;
    private Node tail;
    int cap;

    public LruDemo1(int capacity) {
        this.cap = capacity;
        head = new Node(null, null);
        tail = new Node(null, null);
        head.next = tail;
        tail.pre = head;
    }

    Map<K, Node> map = new HashMap<K, Node>();



}