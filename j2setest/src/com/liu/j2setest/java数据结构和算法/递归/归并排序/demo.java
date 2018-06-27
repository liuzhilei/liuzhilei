package com.liu.j2setest.java数据结构和算法.递归.归并排序;

import java.util.Arrays;

/**
 * Created by liuzhilei on 2017/12/18.
 */
public class demo {

    public static void main(String[] args) {
        int[] array = {1, 3, 2, 5, 9, 4, 7};
        sort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    private static void sort(int[] array, int low, int high) {
        if (low >= high) {
            return;
        }

        int mid = (low + high) / 2;

        sort(array, low, mid);

        sort(array, mid + 1, high);

        merge(array, low, mid, high);

    }

    private static void merge(int[] array, int low, int mid, int high) {
        int left = low;
        int right = mid + 1;
        int j = 0;
        int[] temp = new int[high - low + 1];
        while (left <= mid && right <= high) {
            if (array[left] <= array[right]) {
                temp[j++] = array[left++];
            } else {
                temp[j++] = array[right++];
            }
        }

        while (left <= mid) {
            temp[j++] = array[left++];
        }

        while (right <= high) {
            temp[j++] = array[right++];
        }

        for (int i = 0; i < temp.length; i++) {
            array[low + i] = temp[i];
        }

    }


}
