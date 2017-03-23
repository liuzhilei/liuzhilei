package com.liu.j2setest.thread.java多线程编程核心技术.第四章lock的使用.使用ReentrantReadWriteLock读写锁.读读共享;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by liuzhilei on 2017/3/23.
 */
public class DuDuService {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void read() {
        try {
            lock.readLock().lock();
            System.out.println("读读共享，共享读的时间，线程：" + Thread.currentThread().getName() + ", time = " + System.currentTimeMillis());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }
}
