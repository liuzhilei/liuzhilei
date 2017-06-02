package com.liu.j2setest.java数据结构和算法.简单排序.插入排序;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by liuzhilei on 2017/3/14.
 * 插入排序，性能最好
 * 时间复杂度：O(N^2)
 * 计算方式：假设小数据项在很靠近右边的位置，那么如果移动到左边正确的位置，所有的中间数据项都执行了将近N次的复制。虽然不
 * 是所有的数据项都必须向右移动一位，不过数据平均移动了N/2个移位，这就是执行了N*（N/2）个移位，所以执行效率是O(N^2)
 * 所以说：插入排序对于基本有序的数组来说是非常有效的
 */
public class Charu {

    public static void main(String[] args) {

        int[] theArray = {23, 12};
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
