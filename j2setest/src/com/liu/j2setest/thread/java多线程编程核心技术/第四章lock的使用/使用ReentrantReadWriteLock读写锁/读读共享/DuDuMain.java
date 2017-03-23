package com.liu.j2setest.thread.java多线程编程核心技术.第四章lock的使用.使用ReentrantReadWriteLock读写锁.读读共享;

/**
 * Created by liuzhilei on 2017/3/23.
 */
public class DuDuMain {
    public static void main(String[] args) {
        DuDuService service = new DuDuService();
        Thread thread = new DuDuThreadA(service);
        thread.setName("A");

        Thread thread1 = new DuDuThreadA(service);
        thread1.setName("B");

        thread.start();
        thread1.start();
    }
}
