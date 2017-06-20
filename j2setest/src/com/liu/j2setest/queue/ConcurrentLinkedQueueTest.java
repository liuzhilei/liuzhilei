package com.liu.j2setest.queue;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by liuzhilei on 2017/6/20.
 * ConcurrentLinkedQueue是线程安全的队列，queue中的元素FIFO进行排序，采用cas操作，保证元素的一致性
 */
public class ConcurrentLinkedQueueTest {

    private static Queue<String> queue = new ConcurrentLinkedQueue<String>();

    public static void main(String[] args) {
        new TestThread("ta").start();
        new TestThread("tb").start();

        String value = null;
        Iterator<String> iterator = queue.iterator();
        while (iterator.hasNext()) {
            value = iterator.next();
            System.out.print(value + ",");
        }
        System.out.println();
    }

    static class TestThread extends Thread {

        public TestThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            int i = 0;
            while (i++ < 10) {
                queue.add(Thread.currentThread().getName() + i);
            }
        }
    }


}
