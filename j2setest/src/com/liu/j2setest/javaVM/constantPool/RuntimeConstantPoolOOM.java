package com.liu.j2setest.javaVM.constantPool;

/**
 * Created by liuzhilei on 2017/2/21.
 *
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);

        /**结果：
         * jdk1.6会返回两个false，jdk1.7会返回一个true，一个false
         * jdk1.6中，str1.intern() 会把string复制到永久代，而StringBuilder新建的对象是保存在java堆上，这两个显然不是同一个引用，所以肯定会是false。
         * jdk1.7中，string.intern()不会再复制实例，只是在常量池中记录首次出现的实例引用，所以str1.intern和str1显然是同一个引用，所以是true;
         * 但是对于str2来说，java不是在常量池首次出现，所以肯定不会是同一个引用，所以是false。
         */
    }
}
