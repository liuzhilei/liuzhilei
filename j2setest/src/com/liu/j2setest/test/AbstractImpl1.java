package com.liu.j2setest.test;

/**
 * Created by liuzhilei on 2016/10/17.
 */
public class AbstractImpl1 extends AbstractTest<User1> {

    @Override
    protected void doBusiness(User1 user1) {
        System.out.println("AbstractImpl1 111111111");
    }
}
