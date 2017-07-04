package com.liu.j2setest.thread.java多线程编程核心技术.第四章lock的使用.使用ReentrantLock类.lock区别;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liuzhilei on 2017/7/4.
 * tryLock()尝试获取锁，返回值为是否得到锁;
 * tryLock(long time, TimeUnit unit) 尝试获取锁，如果没拿到，会等待一段时间;
 */
public class tryLockTest {

    public void test() throws InterruptedException {
        final Lock lock = new ReentrantLock();
        try {
            lock.lock();

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (lock.tryLock(10, TimeUnit.SECONDS)) {
                            System.out.println("子线程尝试获得锁成功");
                        } else {
                            System.out.println("子线程尝试获得锁失败");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "子线程");

            t.start();

            Thread.sleep(5000);

        } finally {
            System.out.println("lock unlock 之前");
            lock.unlock();
            System.out.println("lock unlock 之后");
        }
    }

    public static void main(String[] args) {
        try {
            new tryLockTest().test();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
