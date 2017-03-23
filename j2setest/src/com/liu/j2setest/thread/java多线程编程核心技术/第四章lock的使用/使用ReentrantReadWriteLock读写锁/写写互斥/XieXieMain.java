package com.liu.j2setest.thread.java多线程编程核心技术.第四章lock的使用.使用ReentrantReadWriteLock读写锁.写写互斥;

/**
 * Created by liuzhilei on 2017/3/23.
 */
public class XieXieMain {
    public static void main(String[] args) {
        XieXieService service = new XieXieService();
        Thread thread = new XieXieThreadA(service);
        thread.setName("A");

        Thread thread1 = new XieXieThreadB(service);
        thread1.setName("B");

        thread.start();
        thread1.start();
    }
}
