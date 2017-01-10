package com.liu.j2setest.designpattern.SingleTon;

/**
 * Created by liuzhilei on 2016/12/18.
 * 延迟加载单例，懒汉，会有多线程问题
 */
public class SingleTon1 {
    private static SingleTon1 singleTon;

    private SingleTon1() {
    }

    public static SingleTon1 getInstance() {
        if (singleTon == null) {
            return new SingleTon1();
        }
        return singleTon;
    }
}
