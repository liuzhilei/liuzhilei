package com.liu.j2setest.refrencetest;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhilei on 2016/12/7.
 * 软引用
 */
public class SoftReferenceTest {
    public static void main(String[] args) {
        long stime = System.nanoTime();
        List<SoftReference<byte[]>> list = new ArrayList<SoftReference<byte[]>>();
        for (int i = 0; i < 1024; i++) {
            list.add(new SoftReference<byte[]>(new byte[1024 * 1024]));

            list.clear();
        }
        long etime = System.nanoTime();
        System.out.println(etime - stime);
    }
}
