package com.liu.j2setest.java数据结构和算法.简单排序.冒泡排序;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by liuzhilei on 2017/3/14.
 * 冒泡排序：最坏情况是N*(N-1)/2
 * 大O表示法就是O(N2)
 * 除非数据量很小，不然不推荐
 */
public class Maopao {

    public static void maoPao(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[i]) {
                    swap(nums, j, i);
                }
            }
        }
    }

    private static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {5, 3, 1, 6, 8, 2, 9};
        maoPao(nums);
        System.out.println(Arrays.toString(nums));
    }


}
