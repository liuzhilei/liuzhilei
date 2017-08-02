package com.liu.j2setest.thread.java多线程编程核心技术.第四章lock的使用.使用ReentrantLock类.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liuzhilei on 2017/8/2.
 * 该例子说明了，当condition.signal()唤醒等待结点以后，不会立即执行await()方法后面的代码。因为lock.newCondition()获得的
 * 是AQS的一个内部类：ConditionObject。当调用condition.signal()方法唤醒等待的node结点的时候，并不会立即执行await()后的代码，
 * 而是放入了AQS的FIFO等待队列的尾部，只有当AQS队列中前面的node结点都获得锁以后，才能执行到await()后面的代码
 * 了
 */
public class ConditionDemo implements Runnable {
    private Lock lock;
    private Condition condition;

    public ConditionDemo(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        if ("线程0".equals(Thread.currentThread().getName())) {
            thread0Process();
        } else if ("线程1".equals(Thread.currentThread().getName())) {
            thread1Process();
        } else if ("线程2".equals(Thread.currentThread().getName())) {
            thread2Process();
        }
    }

    private void thread0Process() {
        try {
            lock.lock();
            System.out.println("线程0休息5秒");
            Thread.sleep(5000);
            condition.signal();
            System.out.println("线程0唤醒等待线程");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void thread1Process() {
        try {
            lock.lock();
            System.out.println("线程1被阻塞");
            condition.await();
            System.out.println("线程1被唤醒");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void thread2Process() {
        try {
            System.out.println("线程2想要获得锁");
            lock.lock();
            System.out.println("线程2获取锁成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) throws Exception {
        Lock lock1 = new ReentrantLock();
        Condition condition1 = lock1.newCondition();

        ConditionDemo conditionDemo0 = new ConditionDemo(lock1, condition1);
        Thread thread0 = new Thread(conditionDemo0);
        thread0.setName("线程0");

        ConditionDemo conditionDemo1 = new ConditionDemo(lock1, condition1);
        Thread thread1 = new Thread(conditionDemo1);
        thread1.setName("线程1");

        ConditionDemo conditionDemo2 = new ConditionDemo(lock1, condition1);
        Thread thread2 = new Thread(conditionDemo2);
        thread2.setName("线程2");

        thread1.start();
        Thread.sleep(1000);
        thread0.start();
        Thread.sleep(1000);
        thread2.start();


        thread1.join();

        /*
        * 线程1被阻塞
        * 线程0休息5秒
        * 线程2想要获得锁
        *
        * */

    }

}
