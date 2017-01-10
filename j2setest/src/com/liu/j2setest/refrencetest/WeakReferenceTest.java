package com.liu.j2setest.refrencetest;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhilei on 2016/12/7.
 * 弱引用
 */
public class WeakReferenceTest {
    public static void main(String[] args) {
        long stime = System.nanoTime();
        List<WeakReference<byte[]>> list = new ArrayList<WeakReference<byte[]>>();
        for (int i = 0; i < 1024; i++) {
            list.add(new WeakReference<byte[]>(new byte[1024*1024]));
        }
        long etime = System.nanoTime();
        System.out.println(etime - stime);
    }
}
