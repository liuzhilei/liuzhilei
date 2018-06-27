package com.liu.j2setest.java数据结构和算法.简单排序.选择排序;

import java.util.Arrays;

/**
 * Created by liuzhilei on 2017/12/13.
 */
public class demo {
    public static void main(String[] args) {
        int[] array = {18, 10, 13, 15, 14};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    private static void sort(int[] num) {
        for (int i = 0; i < num.length; i++) {
            int minIndex = i;
            for (int j = i; j < num.length; j++) {
                if (num[j] < num[minIndex]) {
                    minIndex = j;
                }
            }
            swap(num, i, minIndex);
        }
    }

    private static void swap(int[] num, int i, int j) {
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }

}
