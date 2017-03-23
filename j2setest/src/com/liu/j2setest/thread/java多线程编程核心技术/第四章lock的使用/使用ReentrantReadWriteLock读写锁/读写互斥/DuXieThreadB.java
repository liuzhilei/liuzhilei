package com.liu.j2setest.thread.java多线程编程核心技术.第四章lock的使用.使用ReentrantReadWriteLock读写锁.读写互斥;

/**
 * Created by liuzhilei on 2017/3/23.
 */
public class DuXieThreadB extends Thread {
    private DuXieService service;

    public DuXieThreadB(DuXieService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.write();
    }
}
