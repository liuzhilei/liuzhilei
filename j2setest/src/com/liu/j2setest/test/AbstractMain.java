
package com.liu.j2setest.test;

import com.liu.j2setest.liu.User;

import java.util.Random;

/**
 * Created by liuzhilei on 2016/10/17.
 */
public class AbstractMain {


    public static void main(String[] args) {
        /*User user = new User1();
        AbstractTest abstractTest = new AbstractImpl1();
        abstractTest.doBusiness(user);

        User user2 = new User2();
        AbstractTest abstractTest1 = new AbstractImpl2();
        abstractTest1.doBusiness(user2);*/

        int random = (new Random()).nextInt(10);
        System.out.println(random);

    }
}
