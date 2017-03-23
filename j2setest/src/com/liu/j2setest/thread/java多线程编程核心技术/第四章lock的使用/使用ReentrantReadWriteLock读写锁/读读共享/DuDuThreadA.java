package com.liu.j2setest.thread.java多线程编程核心技术.第四章lock的使用.使用ReentrantReadWriteLock读写锁.读读共享;

/**
 * Created by liuzhilei on 2017/3/23.
 */
public class DuDuThreadA extends Thread {
    private DuDuService service;

    public DuDuThreadA(DuDuService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.read();
    }
}
