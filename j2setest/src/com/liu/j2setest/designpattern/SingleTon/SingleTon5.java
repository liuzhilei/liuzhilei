package com.liu.j2setest.designpattern.SingleTon;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Created by liuzhilei on 2017/1/6.
 * 静态内部类形式的单例模式
 * 单例模式的最优解决办法
 */
public class SingleTon5 implements Serializable{

    //静态内部类，只有在第一次被引用的时候才会加载，而且放入了jvm的方法区，天然保证了线程安全
    private static class SingleTonHolder {
        //静态初始化，jvm保证线程安全
        private static SingleTon5 instance = new SingleTon5();
    }

    //私有化构造器
    private SingleTon5() {
        if(SingleTonHolder.instance != null){
            throw new RuntimeException("单例模式不允许反射侵入");
        }
    }

    public static SingleTon5 getInstance() {
        return SingleTonHolder.instance;
    }

    /**
     * 如果不写这个方法，那么单例就不能发挥真正的作用，因为正常情况下单例通过序列化反序列以后，得到的肯定不是同一个对象
     * 写了这个方法，当反序列化的时候，会默认调用ObjectInputStream.readObject方法，这个里面会通过反射调用到自定义的readResolve方法，
     * 在readResolve方法中返回这个单例的对象，那么就可以保证任何情况都可以真正的实现单例模式了
     * 而且类一定要实现序列化，要不然怎么会序列化呢
     * @return
     * @throws ObjectStreamException
     */
    private Object readResolve() throws ObjectStreamException {
        return SingleTonHolder.instance;
    }

}
