package com.liu.j2setest.java数据结构和算法.递归.二分查找;

import sun.rmi.server.InactiveGroupException;

/**
 * Created by liuzhilei on 2017/12/13.
 */
public class demo {
    static int[] ints;

    public static void main(String[] args) {
        ints = new int[]{1, 2, 4, 6, 8, 12, 14, 18, 19, 23, 26, 34, 37, 39};
        find(4, 0, ints.length);
    }

    private static int find(int searchKey, int lowBound, int powerBound) {
        int middle = (lowBound + powerBound) / 2;
        if (ints[middle] == searchKey) {
            System.out.println("找到了。。。" + searchKey);
            return searchKey;
        }
        if (powerBound < lowBound) {
            System.out.println("没数据");
            return 0;
        }

        if (searchKey < ints[middle]) {
            return find(searchKey, lowBound, middle);
        } else {
            return find(searchKey, middle, powerBound);
        }


    }
}
