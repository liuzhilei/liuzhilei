package com.liu.j2setest.thread.java多线程编程核心技术.第四章lock的使用.使用ReentrantLock类;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liuzhilei on 2017/7/4.
 * 使用lock和condition实现信号量Semaphore
 */
public class SemaphoreOnLock {
    private final Lock lock = new ReentrantLock();
    private final Condition permitsAvailable = lock.newCondition();
    private int permits;

    public SemaphoreOnLock(int initPermits) {
        try {
            lock.lock();
            //初始化数量
            permits = initPermits;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 阻塞并且直到 permitsAvailable
     *
     * @throws InterruptedException
     */
    public void acquire() throws InterruptedException {
        try {
            lock.lock();
            while (permits <= 0) {
                permitsAvailable.await();
            }
            --permits;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 释放
     */
    public void release() {
        try {
            lock.lock();
            ++permits;
            permitsAvailable.signal();
        } finally {
            lock.unlock();
        }
    }

}
