package com.liu.j2setest.thread.java并发编程实战.第五章基础构建模块;

import java.util.*;

/**
 * Created by liuzhilei on 2017/6/23.
 * 隐藏迭代器
 * 该类包含了隐藏的迭代器，有可能会出现ConcurrentModificationException异常
 */
public class HiddenIterator {

    private final Set<Integer> set = new HashSet<Integer>();

    public synchronized void add(Integer i) {
        set.add(i);
    }

    public synchronized void remove(Integer i) {
        set.remove(i);
    }

    public void addTenThings() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            add(random.nextInt());
        }
        /**
         * 此输出语句就包含了隐藏的迭代器
         * 输出set会调用StringBuilder.append(Object)方法，进而调用容器的toString()方法，标准的toString方法会迭代容器，并在
         * 每个容器上调用toString来生成容器内容的格式化表示
         *
         * 该处可能会抛出ConcurrentModificationException异常，因为在debug模式，生成调试信息的时候，toString会对容器进行迭代
         * 根本原因在于HiddenIterator不是线程安全的，在使用println的set之前，必须获得HiddenIterator的锁。在调试代码和日志代码中通常会忽视这个问题
         */
        System.out.println("DEBUG: 添加十个元素后输出set：" + set);
    }

    public static void main(String[] args) {
        HiddenIterator hiddenIterator = new HiddenIterator();
        //hiddenIterator.addTenThings();

        List<Integer> list = new ArrayList<Integer>();
        List<Integer> llist = new LinkedList<Integer>();
        //hiddenIterator.listtest(llist);

        String s = "1";
        String s1 = "1";
        String s2 = "1";

        System.out.println(s + s1 + s2);
    }
}
