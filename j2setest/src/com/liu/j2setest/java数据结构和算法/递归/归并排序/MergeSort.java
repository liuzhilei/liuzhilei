package com.liu.j2setest.java数据结构和算法.递归.归并排序;

import java.util.Arrays;

/**
 * Created by liuzhilei on 2017/5/31.
 * 归并排序
 * 简介：将两个及以上有序表合并成一个新的有序表，即把待排序序列分成若干个子序列，每个子序列都是有序的，然后把子序列合并成整体有序序列
 * 时间复杂度：O(n*logN)
 * 稳定排序方式
 * 缺点：需要的空间是原始数组的两倍
 */
public class MergeSort {

    static int[] theArray;

    /**
     * @param theArray 待排序数组
     * @param low
     * @param high
     */
    public static void sort(int[] theArray, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = (low + high) / 2;

        //左边
        sort(theArray, low, mid);

        //右边
        sort(theArray, mid + 1, high);

        //归并排序
        merge(theArray, low, mid, high);

    }

    public static void merge(int[] theArray, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        //左指针
        int left = low;
        //右指针
        int right = mid + 1;
        //临时数组的索引
        int k = 0;

        //两边数组小的部分先放到临时数组里面
        while (left <= mid && right <= high) {
            if (theArray[left] <= theArray[right]) {
                temp[k++] = theArray[left++];
            } else {
                temp[k++] = theArray[right++];
            }
        }

        //左边剩余的部分
        while (left <= mid) {
            temp[k++] = theArray[left++];
        }

        //右边剩余的部分
        while (right <= high) {
            temp[k++] = theArray[right++];
        }


        //新数组的数覆盖原数组
        for (int i = 0; i < temp.length; i++) {
            theArray[low + i] = temp[i];
        }

    }

    public static void main(String[] args) {
        theArray = new int[10];
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

        System.out.println("排序之前：");
        System.out.println(Arrays.toString(theArray));
        sort(theArray, 0, theArray.length - 1);
        System.out.println("排序之后:");
        System.out.println(Arrays.toString(theArray));

    }


}
