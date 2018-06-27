package com.liu.j2setest.java数据结构和算法.简单排序.插入排序;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by liuzhilei on 2017/12/13.
 */
public class demo {

    public static void main(String[] args) {
        int[] array = {18, 10, 13};
        sort(array);
        System.out.println(Arrays.toString(array));

    }

    private static void sort(int[] num) {
        for (int i = 0; i < num.length - 1; i++) {
            int j = i + 1;
            int temp = num[j];
            while (i >= 0 && temp < num[i]) {
                num[j] = num[i];
                i--;
                j--;
            }
            num[j] = temp;
        }
    }

}
