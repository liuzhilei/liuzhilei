package com.liu.j2setest.java数据结构和算法.简单排序.插入排序;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by liuzhilei on 2017/3/14.
 * 插入排序，性能最好
 */
public class Charu {

    private static int jiaohuanCount = 0;
    private static Integer[] arr;

    public static void main(String[] args) {

        int bijiaoCount = 0;
        List<Integer> list = new ArrayList<Integer>(10000);
        for (int i = 0; i < 10000; i++) {
            list.add(new Random().nextInt(10000));
        }
        arr = (Integer[]) list.toArray(new Integer[10000]);
        int out, in;
        long startTime = System.currentTimeMillis();
        for (out = 1; out < arr.length; out++) {
            int temp = arr[out];
            in = out;
            while (in > 0 && arr[in - 1] > temp) {
                arr[in] = arr[in - 1];
                --in;
            }
            arr[in] = temp;
        }
        long endTime = System.currentTimeMillis();

        for (int i : arr) {
            System.out.println(i);
        }

        System.out.println(endTime - startTime);
        System.out.println("比较次数：" + bijiaoCount);
        System.out.println("交换次数：" + jiaohuanCount);

    }

    private static void swap(int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
        jiaohuanCount++;
    }
}
