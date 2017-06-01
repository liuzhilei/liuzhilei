package com.liu.j2setest.java数据结构和算法.递归.二分查找;

/**
 * Created by liuzhilei on 2017/5/27.
 * 使用递归进行二分查找
 * 递归的二分查找和非递归的二分查找时间复杂度都是O(logN)。递归的代码简洁，但是效率可能会低一些
 */
public class ErFen2 {

    static int[] ints;

    public static void main(String[] args) {
        ints = new int[]{1, 2, 4, 6, 8, 12, 14, 18, 19, 23, 26, 34, 37, 39};
        find(4, 0, ints.length);
    }

    /**
     * @param searchKey  要查找的值
     * @param lowerBound 最小索引
     * @param upperBound 最大索引
     * @return
     */
    public static int find(int searchKey, int lowerBound, int upperBound) {
        int current = (lowerBound + upperBound) / 2;
        if (ints[current] == searchKey) {
            System.out.println("找到了这个值：" + searchKey);
            return current;
        } else if (lowerBound > upperBound) {
            System.out.println("没找到");
            return 0;
        } else {
            if (searchKey < ints[current]) {
                return find(searchKey, lowerBound, current - 1);
            } else {
                return find(searchKey, current + 1, upperBound);
            }
        }


    }

}
