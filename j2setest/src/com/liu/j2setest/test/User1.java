package com.liu.j2setest.test;

import com.liu.j2setest.liu.User;

/**
 * Created by liuzhilei on 2016/10/17.
 */
public class User1 extends User {
    public static void main(String[] args) {
        System.out.println("user1");
    }

    private String age;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

