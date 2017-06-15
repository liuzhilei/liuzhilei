package com.liu.j2setest.collection.集合迭代器快速失败行为;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by liuzhilei on 2017/6/15.
 */
public class Demo3 {

    public static void main(String[] args) {
        List<Integer> list = new CopyOnWriteArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        ThreadTest1 threadTest = new ThreadTest1(list);
        new Thread(threadTest).start();

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

class ThreadTest1 implements Runnable {

    private List<Integer> list;

    public ThreadTest1(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(10);
            System.out.println(System.currentTimeMillis());
        }
    }
}
