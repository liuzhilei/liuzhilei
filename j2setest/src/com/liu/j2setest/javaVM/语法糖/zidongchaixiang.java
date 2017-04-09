package com.liu.j2setest.javaVM.语法糖;

/**
 * Created by liuzhilei on 2017/4/9.
 * 自动拆箱与装箱，遍历循环，都是语法糖
 */
public class zidongchaixiang {

    public static void main(String[] args) {

        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        Integer h = 300;
        Integer i = 21;

        // true 地址相同
        System.out.println(c == d);//t
        // false 地址不同，==没有遇到算数运算，不会拆箱，指向的是不同地址
        System.out.println(e == f);//f
        // true 地址不同，但是==遇到了算术运算，会自动拆箱，比较了数值
        System.out.println(c == (a + b));//t
        // true 同上
        System.out.println(e == (h + i));//t
        // true equals比较的数值
        System.out.println(c.equals(a + b));//t
        // true 都是包装类，==遇到了算数运算，会自动拆箱，所以比较了数值
        System.out.println(g == (a + b));//t
        // false 因为equals不处理类型转换的关系，所以他们是不同的类型，即便数值一样，但不是同一个类型个，所以是false
        System.out.println(g.equals(a + b));//f

    }
}
