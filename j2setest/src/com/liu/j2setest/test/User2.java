package com.liu.j2setest.test;

import com.liu.j2setest.liu.User;

/**
 * Created by liuzhilei on 2016/10/17.
 */
public class User2 extends User {
    public static void main(String[] args) {
        System.out.println("user2");
    }

    private String hobby;

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
