package com.liu.j2setest.mianshiTest.LeeCode;

import org.apache.commons.collections.ResettableListIterator;

import java.awt.font.NumericShaper;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuzhilei on 2018/2/26.
 * <p/>
 * 给定一个整数数组，返回两个数字的索引，使它们合计成一个特定的目标。
 * 您可能会认为每个输入都只有一个解决方案，并且您可能不会使用相同的元素两次。
 * <p/>
 * 例：
 * 给定nums = [2,7,11,15]，目标= 9，
 * <p/>
 * 由于nums [ 0 ] + nums [ 1 ] = 2 + 7 = 9，
 * 返回[ 0，1 ]。
 */
public class Demo3 {

    public static void main(String[] args) {
        int[] nums = {2, 11, 7, 15};

        int target = 18;
        System.out.println(Arrays.toString(twoSum(nums, target)));
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                result[0] = map.get(target - nums[i]);
                result[1] = i;
            }
            map.put(nums[i], i);
        }

        return result;
    }
}
