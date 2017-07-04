package com.liu.j2setest.thread.java多线程编程核心技术.第四章lock的使用.使用ReentrantLock类;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liuzhilei on 2017/3/23.
 * ReentrantLock是一个可重入的互斥锁。
 * 互斥：ReentrantLock在同一个时间只能被一个线程持有；重入：ReentrantLock可以被单个线程多次获取
 * <p/>
 * ReentrantLock分为“公平锁”和“非公平锁”，ReentrantLock通过一个FIFO的等待队列来管理获得该锁的所有线程。在公平锁的机制
 * 下，线程依次排队获得锁；在非公平锁的机制下，如果锁是可获取的，就会从队列中取出，不管是不是在队头。
 * <p/>
 * Condition是需要和Lock联合使用的：通过Condition中的await()方法，能让线程阻塞[类似于wait()]；通过Condition的signal()方法，
 * 能让唤醒线程[类似于notify()]
 */
public class ReentrantLockTest {
    Lock lock = new ReentrantLock();

    public void test() {
        lock.tryLock();
        lock.lock();
        for (int i = 0; i < 5; i++) {
            System.out.println("ThreadName = " + Thread.currentThread().getName() + " i = " + (i + 1));
        }
        lock.unlock();
    }
}

class Test1Thread1 extends Thread {
    private ReentrantLockTest test1;

    public Test1Thread1(ReentrantLockTest test1) {
        this.test1 = test1;
    }

    @Override
    public void run() {
        test1.test();
    }
}

class Test1Main {
    public static void main(String[] args) {
        ReentrantLockTest test1 = new ReentrantLockTest();
        Thread thread1 = new Test1Thread1(test1);
        Thread thread2 = new Test1Thread1(test1);
        Thread thread3 = new Test1Thread1(test1);
        Thread thread4 = new Test1Thread1(test1);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }
}
