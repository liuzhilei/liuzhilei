package com.liu.j2setest.thread.java多线程编程核心技术.第四章lock的使用.使用ReentrantReadWriteLock读写锁;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by liuzhilei on 2017/7/4.
 * 锁降级：从写锁变成读锁
 * 锁升级：从读锁变成写锁
 * <p/>
 * ReentrantReadWriteLock 支持锁降级，不支持锁升级
 */
public class ReadWriteLockTest {

    /**
     * 不支持锁升级
     * 该方法会发生死锁，因为不支持锁升级，而且读锁没有释放
     */
    public static void suoshengji() {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        readWriteLock.readLock().lock();
        System.out.println("获取到了读锁");
        readWriteLock.writeLock().lock();
        System.out.println("获取到了写锁");
    }

    /**
     * 支持锁降级
     * 该方法正常退出
     */
    public static void suojiangji() {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        readWriteLock.writeLock().lock();
        System.out.println("获取到了写锁");
        readWriteLock.readLock().lock();
        System.out.println("获取到了读锁");
    }

    public static void main(String[] args) {
        suojiangji();
        //suoshengji();
    }
}
