package com.liu.j2setest.java数据结构和算法.高级排序.希尔排序;

import java.util.Arrays;

/**
 * Created by liuzhilei on 2017/6/2.
 * 希尔排序
 * 简介：对于n个整数排序，将这列数按d-增量分组，对各个分组进行插入排序，然后缩小增量，重新分组，对分组排序，直到增量值为1时停止。
 * d-增量分组的含义是每隔d个元素就进行排序，常用的间隔序列是h=3*h-1，h初始值是1。例子用的是h=2*h
 * 希尔排序是插入排序算法的一种，时间复杂度O(N* (logN)^2),比插入排序快O(N^2)，比快速排序慢O(N*logN)
 * 不稳定排序方式
 */
public class ShellSort {

    public static void sort(int[] nums) {
        int len = nums.length / 2;
        while (len >= 1) {
            for (int i = 0; i < len; i++) {
                for (int k = i; k < nums.length - len; k += len) {
                    int j = k + len;
                    int temp = nums[j];
                    while (k >= 0 && nums[k] > temp) {
                        nums[j] = nums[k];
                        j -= len;
                        k -= len;
                    }
                    nums[j] = temp;
                }
            }
            len = len / 2;
        }
    }

    public static void main(String[] args) {
        int[] nums = {15, 2, 10, 8, 9};
        sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
