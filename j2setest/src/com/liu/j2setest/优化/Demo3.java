package com.liu.j2setest.优化;

/**
 * Created by liuzhilei on 2017/7/31.
 * 对于数组赋值，要使用jdk提供的native方法System.arraycopy
 */
public class Demo3 {
    public static void main(String[] args) {

        int size = 10000000;
        int[] array = new int[size];
        int[] copyArray = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        long start = System.currentTimeMillis();
        System.arraycopy(array, 0, copyArray, 0, size);
        long end = System.currentTimeMillis();
        //System.out.println(copyArray.length);
        System.out.println(end - start);

        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            copyArray[i] = array[i];
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
