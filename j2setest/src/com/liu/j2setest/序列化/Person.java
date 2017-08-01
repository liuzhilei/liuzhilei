package com.liu.j2setest.序列化;

import org.msgpack.annotation.Message;

import java.io.Serializable;

/**
 * Created by liuzhilei on 2017/7/31.
 */
@Message
public class Person implements Serializable{
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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
