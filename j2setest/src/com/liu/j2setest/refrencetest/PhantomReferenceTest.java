package com.liu.j2setest.refrencetest;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * Created by liuzhilei on 2017/7/31.
 * 虚引用
 * 如果一个对象和虚引用关联，那么和没有任何引用关联 性质是一样的，在任何时候都可以进行垃圾回收
 * 虚引用必须和引用队列关联使用，当虚引用将要进行回收时，会把引用放入引用队列中。
 */
public class PhantomReferenceTest {
    public static void main(String[] args) throws Exception {
        ReferenceQueue queue = new ReferenceQueue();
        PhantomReference<String> sr = new PhantomReference<String>(new String("abc"), queue);
        System.out.println(sr.get() + "===" + queue.poll());
        System.out.println(sr.get());

    }
}
