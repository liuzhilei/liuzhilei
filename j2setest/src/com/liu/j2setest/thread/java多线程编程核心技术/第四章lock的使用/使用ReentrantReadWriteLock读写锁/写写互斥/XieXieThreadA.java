package com.liu.j2setest.thread.java多线程编程核心技术.第四章lock的使用.使用ReentrantReadWriteLock读写锁.写写互斥;

/**
 * Created by liuzhilei on 2017/3/23.
 */
public class XieXieThreadA extends Thread {
    private XieXieService service;

    public XieXieThreadA(XieXieService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.write();
    }
}
