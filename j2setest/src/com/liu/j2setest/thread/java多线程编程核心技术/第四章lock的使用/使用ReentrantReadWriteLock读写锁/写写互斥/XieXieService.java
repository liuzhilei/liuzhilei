package com.liu.j2setest.thread.java多线程编程核心技术.第四章lock的使用.使用ReentrantReadWriteLock读写锁.写写互斥;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by liuzhilei on 2017/3/23.
 */
public class XieXieService {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void write() {
        try {
            lock.writeLock().lock();
            System.out.println("写写互斥，线程为：" + Thread.currentThread().getName() + ", time=" + System.currentTimeMillis());
            Thread.sleep(2345);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }
}
