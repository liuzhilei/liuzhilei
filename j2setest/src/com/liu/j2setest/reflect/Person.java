package com.liu.j2setest.reflect;

/**
 * Created by liuzhilei on 2017/5/25.
 */
public class Person<T> {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
