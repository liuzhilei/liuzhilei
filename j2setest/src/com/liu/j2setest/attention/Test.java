package com.liu.j2setest.attention;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Auther: liuzhilei
 * @Date: 2019/7/31
 * @Description: 一些注意的小细节
 */
public class Test {

    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            set.add(i);
            set.remove(i - 1);
        }
        //最后输出值为1
        System.out.println(set.size());


        Set<Short> set1 = new HashSet<>();
        for (short i = 0; i < 100; i++) {
            set1.add(i);
            set1.remove(i - 1);
        }
        //存储类型不一样，所以remove并不能删除任何元素，所以最后输出值为100
        System.out.println(set1.size());


        /**
         * 双目运算符中，双目数值提升的问题
         * 1.如果定义了数据类型的变量和未定义数据类型的变量参与双目运算符的后双目运算，那么返回的结果就是范围大（精度高）的类型。
         * 2.如果两个定义了数据类型的变量参与双目运算符的后双目运算，那么返回的结果就是范围大（精度高）的类型。
         * 3.如果直接进行数值的比较，则自动转型为范围大（精度高）的类型。
         */
        Object i = 1 == 1 ? new Integer(3) : new Float(1);
        System.out.println(i);


    }
}
