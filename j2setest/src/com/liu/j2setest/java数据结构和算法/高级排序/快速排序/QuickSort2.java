package com.liu.j2setest.java数据结构和算法.高级排序.快速排序;

import java.util.Arrays;

/**
 * Created by liuzhilei3 on 2018/8/15.
 * 指针法，操作指针进行排序
 * 最快 O(N*logN)，最慢就和冒泡一样O(N2)
 */
public class QuickSort2 {

    private static void quickSort2(int[] arr, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }

        int position = partition(arr, startIndex, endIndex);

        quickSort2(arr, startIndex, position - 1);
        quickSort2(arr, position + 1, endIndex);
    }

    private static int partition(int[] arr, int startIndex, int endIndex) {
        int basic = arr[startIndex];//第一个数作为基准数
        int left = startIndex;
        int right = endIndex;

        //left与right不重合
        while (left != right) {
            //操作右指针
            while (left < right && arr[right] > basic) {
                right--;
            }

            //操作左指针
            while (left < right && arr[left] <= basic) {
                left++;
            }

            //符合条件，调换位置
            if (left < right) {
                int temp = arr[right];
                arr[right] = arr[left];
                arr[left] = temp;
            }

        }

        //left与right重合的时候，调换该位置和基准值
        int temp = arr[left];
        arr[left] = arr[startIndex];
        arr[startIndex] = temp;

        //返回基准值此时的位置
        return left;
    }

    public static void main(String[] args) {
        int[] arr = {4, 6, 3, 5, 2, 7, 1, 8};
        quickSort2(arr, 0, arr.length - 1);

        System.out.println(Arrays.toString(arr));
    }
}
