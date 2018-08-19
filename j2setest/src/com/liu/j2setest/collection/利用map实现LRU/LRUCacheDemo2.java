package com.liu.j2setest.collection.利用map实现LRU;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuzhilei3 on 2018/8/17.
 */
public class LRUCacheDemo2 {

    private Map<String, DoubleLinkNode> cache = new HashMap<>();
    //容量
    private int capacity;

    //lru中的数量
    private int count;

    //头结点
    private DoubleLinkNode head;

    //尾节点
    private DoubleLinkNode tail;

    public LRUCacheDemo2(int capacity) {
        this.capacity = capacity;
        count = 0;

        head = new DoubleLinkNode();
        tail = new DoubleLinkNode();

        head.pre = null;
        head.next = tail;

        tail.next = null;
        tail.pre = head;
    }

    public Integer get(String key) {
        if (!cache.containsKey(key)) {
            return -1;
        }

        DoubleLinkNode node = cache.get(key);
        moveToHead(node);

        return node.value;
    }

    public void set(String key, Integer value) {
        if (cache.containsKey(key)) {
            DoubleLinkNode node = cache.get(key);
            node.value = value;
            moveToHead(node);
            return;
        }

        DoubleLinkNode newNode = new DoubleLinkNode();
        newNode.key = key;
        newNode.value = value;

        //链表接入新元素
        addNode(newNode);

        //加入到map中
        cache.put(key, newNode);

        //链表长度+1
        count++;

        //超长，就删除最后一个元素
        if (count > capacity) {
            removeLastNode();
            count--;
        }

    }

    //把节点移动到头部，即原始位置删除节点，然后把该节点添加到头部
    private void moveToHead(DoubleLinkNode node) {
        removeNode(node);
        addNode(node);
    }

    //删除该节点
    private void removeNode(DoubleLinkNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    //把该节点添加到头部
    private void addNode(DoubleLinkNode node) {
        DoubleLinkNode next = head.next;

        node.pre = head;
        head.next = node;

        next.pre = node;
        node.next = next;
    }

    //超过长度，就删掉最后一个元素，并且把该元素对应的KV从map中删除
    private void removeLastNode() {
        DoubleLinkNode lastNode = tail.pre;
        removeNode(lastNode);

        //map中删除KV
        cache.remove(lastNode.key);
    }
}
