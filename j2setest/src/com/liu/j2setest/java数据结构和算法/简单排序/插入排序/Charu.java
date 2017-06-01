package com.liu.j2setest.java数据结构和算法.简单排序.插入排序;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by liuzhilei on 2017/3/14.
 * 插入排序，性能最好
 * 时间复杂度：O(N^2)
 *
 */
public class Charu {

    public static void main(String[] args) {

        int[] theArray = new int[10];
        theArray[0] = 23;
        theArray[1] = 12;
        theArray[2] = 15;
        theArray[3] = 53;
        theArray[4] = 27;
        theArray[5] = 36;
        theArray[6] = 1;
        theArray[7] = 43;
        theArray[8] = 45;
        theArray[9] = 38;

        sort(theArray);

        System.out.println(Arrays.toString(theArray));
    }

    public static void sort(int[] num) {
        for (int i = 0; i < num.length - 1; i++) {
            int j = i + 1;
            int temp = num[j];

            //待插入数temp 与 排序部分的的最大值即num[i]进行比较
            while (i >= 0 && num[i] > temp) {
                num[j] = num[i];
                i--;
                j--;
            }
            num[j] = temp;

        }
    }

}
