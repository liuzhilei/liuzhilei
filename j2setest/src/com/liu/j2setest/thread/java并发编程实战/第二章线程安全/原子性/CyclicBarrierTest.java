package com.liu.j2setest.thread.java并发编程实战.第二章线程安全.原子性;

import java.util.concurrent.BrokenBarrierException;
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
 * 栅栏用于所有线程相互等待，直到所有线程到达某一个公共点以后，打开栅栏，通过栅栏执行，而且栅栏可以循环使用
 * <p/>
 * 线程到达栅栏位置时，调用await方法，这个方法会阻塞直到所有线程都到达栅栏位置，然后
 * 栅栏打开，所有线程会释放，而栅栏会被重置以便下次使用。
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("此时所有线程到达栅栏位置，执行栅栏操作，await方法必须在栅栏操作执行结束后才会释放阻塞");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("五个人都到达了终点，可以庆祝了!!!");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("栅栏线程执行完毕，await开始结束阻塞，开始执行....................................");
            }
        });

        //栅栏可以循环使用，如果不和栅栏设置的线程数对应，会发生死锁
        for (int i = 1; i <= 10; i++) {
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
            /**
             * await方法会阻塞，直到所有线程都到达栅栏位置，打开栅栏，释放所有线程，此时栅栏也会
             * 被重置用于下次使用
             * 如果成功通过栅栏以后，会为每个线程返回一个唯一的到达索引号
             * 当栅栏的线程执行完毕以后，await才会结束阻塞
             */
            int await = cyclicBarrier.await();
            System.out.println("await方法释放阻塞后的操作" + id + "，成功通过栅栏，返回唯一到达索引号：" + await);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            System.out.println("如果await方法调用超时，或者await阻塞时被中断，所有阻塞的await调用都将被终止并抛出BrokenBarrierException异常");
            e.printStackTrace();
        }
    }
}
