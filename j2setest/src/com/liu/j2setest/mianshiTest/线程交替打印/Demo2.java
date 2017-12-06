package com.liu.j2setest.mianshiTest.线程交替打印;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liuzhilei on 2017/12/6.
 * 利用 lock，condition的await,signal实现
 */
public class Demo2 {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        boolean flag = true;

        Thread thread = new Demo2Thread1(lock,condition,flag);
        Thread thread1 = new Demo2Thread2(lock,condition,flag);
        thread.start();
        thread1.start();
    }
}

class Demo2Thread1 extends Thread {
    private ReentrantLock lock;
    private Condition condition;
    private boolean flag;

    public Demo2Thread1(ReentrantLock lock, Condition condition, boolean flag) {
        this.lock = lock;
        this.condition = condition;
        this.flag = flag;
    }

    @Override
    public void run() {
        try{
            lock.lock();
            for (int i = 0; i < 10; i++) {
                if(flag){
                    condition.await();
                }
                System.out.println("111111");
                flag = true;
                condition.signal();
            }
        }catch (Exception e){

        }finally {
            lock.unlock();
        }


    }
}

class Demo2Thread2 extends Thread {
    private ReentrantLock lock;
    private Condition condition;
    private boolean flag;

    public Demo2Thread2(ReentrantLock lock, Condition condition, boolean flag) {
        this.lock = lock;
        this.condition = condition;
        this.flag = flag;
    }

    @Override
    public void run() {
        try{
            lock.lock();
            for (int i = 0; i < 10; i++) {
                if(!flag){
                    condition.await();
                }
                System.out.println("222222");
                flag = false;
                condition.signal();
            }
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }
}