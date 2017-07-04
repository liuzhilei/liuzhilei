package com.liu.j2setest.thread.java多线程编程核心技术.第四章lock的使用.使用ReentrantLock类.lock区别;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liuzhilei on 2017/7/4.
 * lock.lockInterruptibly() 获取锁，并在锁持有的过程中可以被中断;
 */
public class lockInterruptiblyTest {
    public void test() throws InterruptedException {
        final Lock lock = new ReentrantLock();
        try {
            /**
             * 如果lock没有被另外一个线程保持，则获取该锁后立即返回，锁的保持计数为1
             */
            lock.lock();

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    /**
                     * lockInterruptibly() 会被中断
                     */
                    try {
                        lock.lockInterruptibly();
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + " interrupted............ 被中断");
                        e.printStackTrace();
                    }

                }
            }, "子线程");

            t.start();

            Thread.sleep(2000);

            /**
             * 有效，可以中断
             */
            t.interrupt();

        } finally {
            /*System.out.println("lock unlock 之前");
            lock.unlock();
            System.out.println("lock unlock 之后");*/
        }
    }

    public static void main(String[] args) {
        try {
            new lockInterruptiblyTest().test();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
