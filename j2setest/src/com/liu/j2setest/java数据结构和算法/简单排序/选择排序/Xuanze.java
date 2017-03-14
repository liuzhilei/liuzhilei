package com.liu.j2setest.java数据结构和算法.简单排序.选择排序;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by liuzhilei on 2017/3/14.
 * 对冒泡排序进行了优化，交换次数从O(N2)较少到O(N),但是比较次数依然是O(N2)
 * <p/>
 * 应用场景：
 * 当交换次数耗时明显低于比较耗时的情况下，可以使用选择排序
 */
public class Xuanze {

    private static int jiaohuanCount = 0;
    private static Integer[] arr;

    public static void main(String[] args) {

        int bijiaoCount = 0;
        List<Integer> list = new ArrayList<Integer>(10000);
        for (int i = 0; i < 10000; i++) {
            list.add(new Random().nextInt(10000));
        }
        arr = (Integer[]) list.toArray(new Integer[10000]);
        int out, in, min;
        long startTime = System.currentTimeMillis();
        for (out = 0; out < arr.length - 1; out++) {
            min = out;
            for (in = out + 1; in < arr.length; in++) {
                if (arr[min] > arr[in]) {
                    min = in;
                    swap(min, out);
                }
                bijiaoCount++;
            }
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
