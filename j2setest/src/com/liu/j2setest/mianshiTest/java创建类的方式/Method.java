package com.liu.j2setest.mianshiTest.java创建类的方式;

import com.liu.j2setest.designpattern.SingleTon.SingleTon5;
import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * Created by liuzhilei3 on 2018/8/20.
 */
public class Method {

    public static void main(String[] args) throws Exception {
        //1.new类
        NewClassDemo demo1 = new NewClassDemo();

        /**2.反射 */
        //2.1 利用Class的newInstance
        NewClassDemo demo2 = NewClassDemo.class.newInstance();

        //2.2 利用Constructor的newInstance
        Constructor constructor = NewClassDemo.class.getConstructor();
        NewClassDemo demo3 = (NewClassDemo) constructor.newInstance();

        //3.序列化反序列化
        // 详见serializable包

        //4.利用unsafe，直接通过内存偏移地址获取，不会调用构造方法，可以打破有防御的单例
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);
        NewClassDemo demo4 = (NewClassDemo) unsafe.allocateInstance(NewClassDemo.class);

        //5.clone,不会调用构造方法
        NewClassDemo demo5 = (NewClassDemo) demo3.clone();
    }
}
