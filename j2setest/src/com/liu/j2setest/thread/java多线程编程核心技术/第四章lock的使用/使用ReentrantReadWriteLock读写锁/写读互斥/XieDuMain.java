package com.liu.j2setest.thread.java多线程编程核心技术.第四章lock的使用.使用ReentrantReadWriteLock读写锁.写读互斥;

import com.liu.j2setest.thread.java多线程编程核心技术.第四章lock的使用.使用ReentrantReadWriteLock读写锁.读写互斥.DuXieService;
import com.liu.j2setest.thread.java多线程编程核心技术.第四章lock的使用.使用ReentrantReadWriteLock读写锁.读写互斥.DuXieThreadA;
import com.liu.j2setest.thread.java多线程编程核心技术.第四章lock的使用.使用ReentrantReadWriteLock读写锁.读写互斥.DuXieThreadB;

/**
 * Created by liuzhilei on 2017/3/23.
 */
public class XieDuMain {
    public static void main(String[] args) {
        DuXieService service = new DuXieService();
        Thread thread = new DuXieThreadA(service);
        thread.setName("A");

        Thread thread1 = new DuXieThreadB(service);
        thread1.setName("B");

        thread1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.start();
    }
}
