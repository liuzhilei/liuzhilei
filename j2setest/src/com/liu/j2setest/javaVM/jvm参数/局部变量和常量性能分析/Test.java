package com.liu.j2setest.javaVM.jvm参数.局部变量和常量性能分析;

/**
 * Created by liuzhilei on 2017/7/7.
 * -Xint：全部使用字节码解释运行
 * -Xcomp：全部被编译成机器码执行
 * -Xmixed: 使用混合编译,jdk1.8默认执行方式
 * <p/>
 * 使用-Xint执行结果：
 * 22933
 * 20207
 * <p/>
 * 使用-Xcomp执行结果：
 * 708
 * 714
 * <p/>
 * 使用-Xmixed执行结果
 * 702
 * 721
 * <p/>
 * 可以看到使用字节码解释运行的时间很长，而且test1和test2的执行时间相差两秒
 * 原因：
 * test1方法中，for循环每次都是从常量池中获取Integer.MAX_VALUE的值，test2方法中，Integer.MAX_VALUE赋给了
 * 局部变量len，所以for循环每次都是用len来进行比较，性能就差在这里
 */
public class Test {
    public static void main(String[] args) {
        test1();
        test2();
    }

    public static int test1() {
        long start = System.currentTimeMillis();
        int sum = 0;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        return sum;
    }

    public static int test2() {
        long start = System.currentTimeMillis();
        int sum = 0;
        for (int i = 0, len = Integer.MAX_VALUE; i < len; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        return sum;
    }
}
