package com.liu.j2setest.java数据结构和算法.递归.二分查找;

/**
 * Created by liuzhilei on 2017/5/27.
 * 二分查找
 * 不使用递归的方式进行二分查找
 */
public class ErFen1 {

    static int[] ints;

    public static void main(String[] args) {
        ints = new int[]{1, 2, 4, 6, 8, 12, 14, 18, 19, 23, 26, 34, 37, 39};

        System.out.println(find(18));
    }

    public static int find(int searchKey) {
        int lowerBound = 0;
        int upperBound = ints.length - 1;
        int current;
        while (true) {
            current = (lowerBound + upperBound) / 2;
            if (ints[current] == searchKey) {
                System.out.println("找到了这个值: " + searchKey);
                return current;
            }
            if (upperBound < lowerBound) {
                System.out.println("没有发现");
                return 0;
            }

            if (searchKey < ints[current]) {
                upperBound = current - 1;
            } else {
                lowerBound = current + 1;
            }
        }

    }
}
