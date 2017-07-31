package com.liu.j2setest.refrencetest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhilei on 2016/12/7.
 * 强引用
 */
public class RefrenceTest {
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<byte[]>();
        for (int i = 0; i < 1024; i++) {
            list.add(new byte[1024 * 1024]);
        }
    }
}
