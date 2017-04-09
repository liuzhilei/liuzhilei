package com.liu.j2setest.thread.maintest;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liuzhilei on 2017/4/5.
 * 信号量测试
 * <p/>
 * 信号量相当于一个计数器，如果线程想要访问某个资源，先从信号量中通过acquire拿到许可，同时信号量的计数器减1
 * 如果此时信号量的内部大于0，就证明有可用资源，就可以执行，当访问完这一资源以后，必须释放这个信号量release。
 * <p/>
 * 信号量的作用是 可以指定固定的线程数去访问资源，可以利用信号量实现线程池
 * <p/>
 * 互斥锁和信号量区别：
 * 互斥锁和信号量都是操作系统为并发变成设计的概念，互斥锁非0就是1，信号量可以有多个线程访问
 * 互斥锁在java中用ReentrantLock表示，信号量用Semaphore表示
 * ReentrantLock：tryLock尝试获得锁，成功返回true继续执行，失败返回false
 * Semaphore：acquire拿到许可，计数器减1，release释放资源，计数器又加1
 */
public class SemaphoreTest {
    private static Semaphore semaphore = new Semaphore(2);
    private static int count = 0;

    static class myThread extends Thread {

        @Override
        public void run() {
            try {
                while (true) {
                    //拿到执行的许可，然后semaphore内部计数器减1，大于0就可以继续，不然就处于阻塞状态
                    semaphore.acquire();

                    Thread.sleep(500);
                    System.out.println(Thread.currentThread().getName() + " 为当前线程，count = " + count++);

                    //使用完之后，释放资源，此时内部计数器又加1
                    semaphore.release();
                    //System.out.println("当前可用线程数：" + semaphore.availablePermits());
                    Thread.sleep(500);
                }

                /*for (int i = 0; i < 10; i++) {
                    System.out.println("i = " + i);
                    //拿到执行的许可，然后semaphore内部计数器减1，大于0就可以继续，不然就处于阻塞状态
                    semaphore.acquire();
                    Thread.sleep(500);
                    System.out.println(Thread.currentThread().getName() + " 为当前线程，count = " + count++);
                    //使用完之后，释放资源，此时内部计数器又加1
                    semaphore.release();
                    Thread.sleep(500);
                }*/

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new myThread();
        Thread t2 = new myThread();
        Thread t3 = new myThread();

        t1.start();
        t2.start();
        t3.start();
    }
}
