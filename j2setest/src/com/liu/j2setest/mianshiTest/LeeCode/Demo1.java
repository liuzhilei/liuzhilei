package com.liu.j2setest.mianshiTest.LeeCode;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuzhilei on 2018/1/2.
 * 给定一个整数数组，返回两个数字的索引，使它们加起来成为一个特定的目标。相同元素不能重复使用
 * <p/>
 * 要求：这个函数twoSum必须要返回能够相加等于目标数字的两个数的索引，且index1必须要小于index2。
 * 请注意一点，你返回的结果（包括index1和index2）都不是基于0开始的。你可以假设每一个输入肯定只有一个结果。
 * 例如：
 * 给定nums = [2,7,11,15]，目标= 9，
 * 由于nums [ 0 ] + nums [ 1 ] = 2 + 7 = 9，
 * 返回[ 0，1 ]。
 * <p/>
 * 思路：
 * 数组中两个整数相加得到目标值，即a+b=target; 也就是a = target - b;可以理解为找到a和target-a的数所在的索引即可
 */
public class Demo1 {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15, 5, 3};
        int target = 17;

        Demo1 demo1 = new Demo1();

        //int[] method1 = demo1.method1(nums, target);
        //System.out.println(Arrays.toString(method1));

        //int[] method2 = demo1.method2(nums, target);
        //System.out.println(Arrays.toString(method2));

        int[] method3 = demo1.method3(nums, target);
        System.out.println(Arrays.toString(method3));
    }


    /**
     * 方法1：遍历数组，找到a和target-a两个数所在的位置
     * 时间复杂度：O(N^2)
     */
    public int[] method1(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            int startNum = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                int endNum = target - startNum;
                if (nums[j] == endNum) {
                    result[0] = i;
                    result[1] = j;
                    break;
                }
            }
        }
        return result;
    }


    /**
     * 方法2：对数组进行排序，然后定义左右两个指针。
     * 如果相加大于target，右指针左移；如果小于target，左指针右移
     * 因为排序的时间复杂度 O(N*logN)，查找的时间复杂度 O(N)，所以该方法的时间复杂度是O(N*logN)
     */

    class Node implements Comparable<Node> {
        int arrayIndex;
        int arrayNum;

        public Node(int arrayIndex, int arrayNum) {
            this.arrayIndex = arrayIndex;
            this.arrayNum = arrayNum;
        }

        /**
         * 按照arrayNum从小到大排列
         * 当前对象 - 对比对象 > 0 , 否则小于0
         *
         * @param o
         * @return
         */
        @Override
        public int compareTo(Node o) {
            if (this.arrayNum > o.arrayNum) {
                return 1;
            } else if (this.arrayNum < o.arrayNum) {
                return -1;
            }
            return 0;
        }

    }

    public int[] method2(int[] nums, int target) {
        int[] result = new int[2];

        //先排序
        Node[] temp = new Node[nums.length];
        for (int i = 0; i < nums.length; i++) {
            temp[i] = new Node(i, nums[i]);
        }
        //排序的时间复杂度 O(N*logN)
        Arrays.sort(temp);

        //定义左指针和右指针
        int left = 0;
        int right = temp.length - 1;

        //按照指针来查找数组 查找的时间复杂度 O(N)
        while (left < right) {
            int addTemp = temp[left].arrayNum + temp[right].arrayNum;
            if (addTemp == target) {
                result[0] = temp[left].arrayIndex;
                result[1] = temp[right].arrayIndex;
                break;
            } else if (addTemp < target) {
                left++;
            } else {
                right--;
            }
        }
        return result;
    }


    /**
     * 方法三：利用map进行查找,时间复杂度O(N)
     */
    public int[] method3(int[] nums, int target) {
        int[] result = new int[2];

        //定义map，把数组放入map中
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        //在map中查找
        for (int i = 0; i < nums.length; i++) {
            int endNum = target - nums[i];
            if (map.containsKey(endNum) && map.get(endNum) != i) {
                int index = map.get(endNum);
                if (nums[i] < endNum) {
                    result[0] = i;
                    result[1] = index;
                } else {
                    result[0] = index;
                    result[1] = i;
                }
                break;
            }
        }

        return result;
    }


}
