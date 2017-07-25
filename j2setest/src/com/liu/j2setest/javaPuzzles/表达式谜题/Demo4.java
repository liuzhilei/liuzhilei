package com.liu.j2setest.javaPuzzles.表达式谜题;

/**
 * Created by liuzhilei on 2017/7/25.
 */
public class Demo4 {
    public static void main(String[] args) {
        System.out.println(Long.toHexString(0x100000000L + 0xcafebabe));

        System.out.println(Long.toHexString(0x100000000L + 0xcafebabeL));

        System.out.println(Long.toString(0xcafebabe));

        System.out.println(Integer.toString(0xcafebabe));
    }
}
