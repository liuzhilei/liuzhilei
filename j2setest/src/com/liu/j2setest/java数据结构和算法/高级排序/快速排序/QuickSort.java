package com.liu.j2setest.java数据结构和算法.高级排序.快速排序;

import java.util.Arrays;

/**
 * Created by liuzhilei on 2017/6/4.
 * 快速排序
 * 简介：
 * 1.先从数列中取出一个数作为基准数
 * 2.分区过程，将比这个数大的数全部放在他的右边，比这个数小的数全部放在左边
 * 3.利用递归，重复第二步，知道各区间只有一个数为止
 * <p/>
 * 时间复杂度：O(N*logN)
 * 不稳定排序方式
 */
public class QuickSort {

    public static void sort(int[] nums, int low, int high) {
        if (low < high) {
            int i = partition(nums, low, high);

            sort(nums, low, i - 1);

            sort(nums, i + 1, high);
        }

    }

    //分区
    public static int partition(int[] nums, int low, int high) {
        int key = nums[low];//基准数
        int left = low;//左指针
        int right = high;//右指针

        //基准数右边的数字如果比基准数大，右指针就左移
        while (left < right && nums[right] >= key) {
            right--;
        }

        //此时，右边的数比左边的数小
        if (left < right) {
            nums[left] = nums[right];
            left++;
        }

        //基准数左边的数字如果比基准数小，左指针就右移
        while (left < right && nums[left] <= key) {
            left++;
        }

        //此时，左边的数比基准数大
        if (left < right) {
            nums[right] = nums[left];
            right--;
        }
        //最后把基准数赋给nums[left]
        nums[left] = key;

        return left;
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 8, 6, 10, 7, 2};
        sort(nums, 0, nums.length - 1);
        /*int[] nums = {3, 8, 6, 10, 7, 2};
        partition(nums, 0, nums.length - 1);*/

        System.out.println(Arrays.toString(nums));
    }
}
