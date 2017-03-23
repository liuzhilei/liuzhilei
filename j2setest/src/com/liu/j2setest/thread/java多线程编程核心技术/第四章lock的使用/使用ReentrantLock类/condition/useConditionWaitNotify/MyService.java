package com.liu.j2setest.thread.java多线程编程核心技术.第四章lock的使用.使用ReentrantLock类.condition.useConditionWaitNotify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liuzhilei on 2017/3/23.
 * <p/>
 * 注意调用await的时候，要持有lock的锁(同步监视器)
 */
public class MyService {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void await() {
        try {
            lock.lock();
            System.out.println("A");
            //线程进入等待状态
            condition.await();
            System.out.println("B");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("锁被释放了");
        }
    }

    public void signal() {
        try {
            lock.lock();
            System.out.println("signal的时间为：" + System.currentTimeMillis());
            condition.signal();
        }finally {
            lock.unlock();
        }
    }


}
