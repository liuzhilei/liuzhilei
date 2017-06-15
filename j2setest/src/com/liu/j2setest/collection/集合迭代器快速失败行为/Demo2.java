package com.liu.j2setest.collection.集合迭代器快速失败行为;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by liuzhilei on 2017/6/15.
 * <p/>
 * 多线程下的迭代器快速失败：一个线程在对集合进行增删操作，另一个线程在利用迭代器进行遍历
 */
public class Demo2 {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        ThreadTest threadTest = new ThreadTest(list);
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

class ThreadTest implements Runnable {

    private List<Integer> list;

    public ThreadTest(List<Integer> list) {
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
