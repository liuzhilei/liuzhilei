package com.liu.j2setest.thread.java并发编程实战.第二章线程安全.原子性;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by liuzhilei on 2017/3/12.
 * CyclicBarrier 栅栏测试
 * <p/>
 * 场景：
 * 五个人跑步，规定所有人跑到终点以后，开始喝啤酒，只要一个人没跑到终点，就不能喝酒
 * <p/>
 * 定义：允许一组线程相互等待，当所有线程到达某个公共点以后，栅栏打开，所有线程通过栅栏继续执行。
 * 每次调用await()方法时候，栅栏计数器将+1，到达临界值以后，栅栏打开。
 * <p/>
 * 闭锁和栅栏区别：
 * 闭锁用于所有线程等待一个外部事件的发生；
 * 栅栏用于所有线程相互等待，直到所有线程到达某一个公共点以后，打开栅栏，通过栅栏执行
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("五个人都到达了终点，可以庆祝了!!!");
            }
        });

        for (int i = 1; i <= 5; i++) {
            new Thread(new Running(i, cyclicBarrier)).start();
        }


    }

}

class Running implements Runnable {
    private int id;
    private CyclicBarrier cyclicBarrier;

    public Running(int id, CyclicBarrier cyclicBarrier) {
        this.id = id;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            System.out.println("第" + id + "开始跑步了...");
            Thread.sleep(500);
            System.out.println("第" + id + "个人到达终点了");
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
