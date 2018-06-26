package com.liu.j2setest.designpattern.SingleTon;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by liuzhilei3 on 2018/6/26.
 * AtomicReference的作用是对"对象"进行原子操作
 * 利用了CAS操作，可以在不使用synchronized和lock的情况下，编写线程安全的单例模式
 * 静态内部类，枚举，饿汉式其实用的是classLoader的线程安全机制实现的单例模式，classLoader里面的loadClass方法其实也用到了synchronized
 */
public class SingleTon6 {
    private static AtomicReference<SingleTon6> reference = new AtomicReference<>();

    private SingleTon6() {
    }

    public static SingleTon6 getInstance() {
        for (; ; ) {
            SingleTon6 singleTon6 = reference.get();
            if (null != singleTon6) {
                return singleTon6;
            }

            singleTon6 = new SingleTon6();
            if (reference.compareAndSet(null, singleTon6)) {
                return singleTon6;
            }
        }
    }
}
