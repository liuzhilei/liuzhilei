package com.liu.j2setest.designpattern.SingleTon;

/**
 * Created by liuzhilei on 2017/1/6.
 * 静态内部类形式的单例模式
 * 单例模式的最优解决办法
 */
public class SingleTon5 {

    private static class SingleTonHolder {
        //静态初始化，jvm保证线程安全
        private static SingleTon5 instance = new SingleTon5();
    }

    //私有化构造器
    private SingleTon5() {
    }

    public static SingleTon5 getInstance() {
        return SingleTonHolder.instance;
    }

}
