package com.liu.j2setest.thread.第三章对象的共享.不变性;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by liuzhilei on 2017/3/14
 * 在可变对象基础上构建不可变类
 * <p/>
 * 不可变对象需要满足的条件：
 * 1.对象创建以后就不能再修改
 * 2.对象的所有域都是final类型
 * 3.对象是正确创建的（在对象的创建期间，this引用没有逸出）
 */
public final class Test {
    private final Set<String> names = new HashSet<String>();

    public Test() {
        names.add("刘志磊");
        names.add("金晶晶");
        names.add("小狗子");
    }

    public boolean isExist(String str) {
        return names.contains(str);
    }

    public static void main(String[] args) {
        System.out.println(new Test().isExist("金晶晶"));
    }

}
