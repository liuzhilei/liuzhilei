package com.liu.j2setest.designpattern.SingleTon;

/**
 * Created by liuzhilei on 2016/12/18.
 * 初始化就加载，饿汉，不会有线程同步问题，但是程序一开始不需要这个类，而且加载此类需要时间很长，会影响服务器性能
 */
public class SingleTon2 {
    private static SingleTon2 singleTon = new SingleTon2();

    private SingleTon2() {
    }

    public SingleTon2 getInstance() {
        return singleTon;
    }
}
