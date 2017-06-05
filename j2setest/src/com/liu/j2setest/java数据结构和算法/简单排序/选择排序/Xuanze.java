package com.liu.j2setest.java数据结构和算法.简单排序.选择排序;

import javax.xml.namespace.QName;
import java.util.Arrays;

/**
 * Created by liuzhilei on 2017/3/14.
 * 对冒泡排序进行了优化，交换次数从O(N2)较少到O(N),但是比较次数依然是O(N2)
 * <p/>
 * 简介：每一趟从待排序的元素中选出最小的元素，顺序放在排好序的元素的最后，直到全部排完
 * 时间复杂度：O(N^2)
 */
public class Xuanze {

    public static void sort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i; j < nums.length; j++) {
                if (nums[minIndex] > nums[j]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                swap(nums, minIndex, i);
            }
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {3, 5, 2, 7, 9, 1, 4, 8};
        sort(nums);
        System.out.println(Arrays.toString(nums));
    }

}
