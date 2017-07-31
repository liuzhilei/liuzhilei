package com.liu.j2setest.refrencetest;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhilei on 2016/12/7.
 * 弱引用，当执行垃圾回收的时，弱引用就会被回收
 */
public class WeakReferenceTest {
    public static void main(String[] args) {

        WeakReference<String > sr = new WeakReference<String>(new String("abc"));
        System.out.println(sr.get());
        System.gc();
        System.out.println(sr.get());
    }
}
