package com.liu.j2setest.mianshiTest.LeeCode;

/**
 * Created by liuzhilei3 on 2018/10/16.
 * 给定一个有序int数组，返回不重复字符的个数，并且执行完方法以后，数组的前几个一定是有序的，后面的是什么都没有关系
 * 例如： {0, 0, 0, 1, 1, 1, 2, 2, 3, 3, 4}，输出5，并且数组前几个字符一定是0,1,2,3,4
 * {1, 1, 2},输出2，并且数组的前两个字符一定是1,2
 */
public class Demo9 {
    public static void main(String[] args) {
        int[] nums = {0, 0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int[] nums1 = {1, 1, 2};
        System.out.println(removeDuplicates(nums));
    }

    public static int removeDuplicates(int[] nums) {
        int i= nums.length > 0 ? 1 : 0;
        for(int n : nums){
            if(n > nums[i - 1]){
                nums[i++] = n;
            }
        }
        return i;
    }
}
