package com.liu.j2setest.java数据结构和算法.简单排序.冒泡排序;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by liuzhilei on 2017/3/14.
 * 冒泡排序：最坏情况是N*(N-1)/2
 * 大O表示法就是O(N2)
 * 除非数据量很小，不然不推荐
 */
public class Maopao {

    private static int jiaohuanCount = 0;
    private static Integer[] arr;

    public static void main(String[] args) {
        int bijiaoCount = 0;
        List<Integer> list = new ArrayList<Integer>(10000);
        for (int i = 0; i < 10000; i++) {
            list.add(new Random().nextInt(10000));
        }
        arr = (Integer[]) list.toArray(new Integer[10000]);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] < arr[j]) {
                    swap(i, j);

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
