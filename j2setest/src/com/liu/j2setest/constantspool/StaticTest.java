package com.liu.j2setest.constantspool;

/**
 * Created by liuzhilei on 2017/2/13.
 */
public class StaticTest {
    private static int i = 1;
    private static final Integer integer = 2;
    private int a = 3;
    private final int finala = 4;
    private String str = "11111111111111";

    /**
     * 注意：
     * 用final修饰的基本数据类型，因为已经不可变，所以会放到常量池中
     * String类型的变量本身就是不可变的，所以肯定会放到常量池中
     * final修饰的对象，即便是基本数据类型的包装类，都不会放到常量池，因为final修饰的是对象的引用，只要对象的地址不发生变化，对象本身通过get，set发生变化还是可以的，所以不可能放到常量池
     *
     * 常量池在方法区中
     */
}
