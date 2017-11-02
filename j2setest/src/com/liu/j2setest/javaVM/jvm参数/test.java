package com.liu.j2setest.javaVM.jvm参数;

/**
 * Created by liuzhilei on 2017/7/11.
 * -XX:+PrintCompilation 使用该参数可以简单的看出字节码编译成本地代码的过程如下：
 * <p/>
 * 64    1             java.lang.String::charAt (29 bytes)
 * 70    2             java.lang.String::hashCode (55 bytes)
 * 70    3             java.lang.String::indexOf (70 bytes)
 * 78    4             sun.misc.ASCIICaseInsensitiveComparator::compare (126 bytes)
 * <p/>
 * 如果使用-Xint，就不会打印上面数据，因为就不会编译成机器码
 * 如果使用-Xcomp,会输出更多的编译过程，因为会先编译成机器码，然后再执行
 * <p/>
 * -XX:+CITime显示编译时间
 * -XX:+PrintCompilation 和 -XX:+CITime 可以一起使用，更好的理解JIT编译器
 * 如果配置为 -Xint -XX:+PrintCompilation -XX:+CITime，那么编译时间会显示为0，因为是字节码解释运行，不需要编译
 *
 * C1就是普通编译，编译成字节码
 * C2就是jit编译，编译成机器码
 */
public class test {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++)
            System.out.println(i);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
