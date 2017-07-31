package com.liu.j2setest.refrencetest;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhilei on 2016/12/7.
 * 软引用，只有在内存不足的时候，jvm才会回收软引用对象
 * 设置堆内存大小：-Xms2m -Xmx2m
 * 下面代码会当空间不足，会进行垃圾回收，软引用会在内存不足的情况下被jvm回收
 */
public class SoftReferenceTest {
    public static void main(String[] args) {
        SoftReference<String>[] str = new SoftReference[100000];
        for (int i = 0; i < str.length; i++) {
            str[i] = new SoftReference<String>(new String("abc"));
        }
        System.out.println(str[0].get());


    }
}
