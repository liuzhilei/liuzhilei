package com.liu.j2setest.thread.java多线程编程核心技术.第四章lock的使用.使用ReentrantLock类.lock区别;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liuzhilei on 2017/7/4.
 * lock()获取锁，在锁持有的过程中执行中断无效，即不能被中断;
 * tryLock()尝试获取锁，返回值为是否得到锁;
 * tryLock(long time, TimeUnit unit) 尝试获取锁，如果没拿到，会等待一段时间;
 * lock.lockInterruptibly() 获取锁，并在锁持有的过程中可以被中断;
 * <p/>
 * lock()一般要执行在代码try{...} finally{...} 中
 */
public class LockTest {

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
                     * 锁被其他线程持有没有被释放，此时线程t获取lock锁，由于
                     * 线程调度，不可能获取到，所以<b>会一直阻塞</b>除非其他线程释放了锁，线程t才能得到锁
                     */
                    lock.lock();
                    //下面代码是模仿sleep
                    long start = System.currentTimeMillis();
                    while (true) {
                        if (System.currentTimeMillis() - start > 5000) {
                            System.out.println("用while模仿sleep延迟了五秒，结束。");
                            break;
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + " 退出");

                }
            }, "子线程");

            t.start();

            Thread.sleep(2000);

            /**
             * 线程t通过lock()拿到锁，对t执行中断是无效的
             */
            t.interrupt();

        } finally {
            System.out.println("lock unlock 之前");
            lock.unlock();
            System.out.println("lock unlock 之后");
        }
    }

    public static void main(String[] args) {
        try {
            new LockTest().test();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
