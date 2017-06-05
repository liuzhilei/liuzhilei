package com.liu.j2setest.java数据结构和算法.高级排序.基数排序;

import java.util.Arrays;

/**
 * Created by liuzhilei on 2017/6/5.
 * 基数排序
 * 简介：基数排序又称“桶子法”，利用键值的部分资讯，将要排序的元素分配制某些“桶”中，达到排序的目的
 * 时间复杂度：O(N*log(2)N)，N*log以2为底N的对数
 * 稳定排序方式
 */
public class RadixSort {

    /**
     * @param nums 待排序数组
     * @param d    基数，和数组中最大的的位数相同
     */
    public static void radix(int nums[], int d) {
        int k = 0;
        int n = 1;//被除数：个，十，百，千 ......

        int len = nums.length;
        //定义一个桶，将临时数据放到桶对应的位置上
        int[][] radixArray = new int[len][len];
        //临时数组，表示第几个桶
        int[] tempArray = new int[len];

        while (n <= d) {
            for (int i = 0; i < len; i++) {
                //每个数据项的第个，十，百，千......上的数字
                int temp = (nums[i] / n) % 10;
                radixArray[temp][tempArray[temp]] = nums[i];
                tempArray[temp]++;
            }

            for (int i = 0; i < len; i++) {
                if (tempArray[i] != 0) {
                    for (int j = 0; j < tempArray[i]; j++) {
                        nums[k] = radixArray[i][j];
                        k++;
                    }
                }
                tempArray[i] = 0;
            }

            k = 0;
            n = n * 10;
        }
    }

    public static void main(String[] args) {
        int[] nums = {2221, 125, 323, 9, 1, 4, 8, 7, 6, 0, 15, 34, 32};
        radix(nums, 1000);

        System.out.println(Arrays.toString(nums));
    }

}
