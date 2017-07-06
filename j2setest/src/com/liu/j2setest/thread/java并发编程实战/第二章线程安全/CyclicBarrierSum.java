package com.liu.j2setest.thread.java并发编程实战.第二章线程安全;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by liuzhilei on 2017/7/6.
 * 利用栅栏 共享变量求和
 * 另见闭锁来计算求和的例子{@link com.liu.j2setest.thread.java并发编程实战.第二章线程安全.原子性.CountDownLatchSum}
 */
public class CyclicBarrierSum {
    private static int count;

    static class innerThread implements Runnable {
        private CyclicBarrier cyclicBarrier;

        public innerThread(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            count++;

            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(1000, new Runnable() {
            @Override
            public void run() {
                System.out.println("计算后的值为：" + count);
            }
        });

        for (int i = 0; i < 1000; i++) {
            new Thread(new innerThread(cyclicBarrier)).start();
        }

    }


}