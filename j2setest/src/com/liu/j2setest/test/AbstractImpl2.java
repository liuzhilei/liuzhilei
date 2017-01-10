package com.liu.j2setest.test;

/**
 * Created by liuzhilei on 2016/10/17.
 */
public class AbstractImpl2 extends AbstractTest<User2> {
    @Override
    protected void doBusiness(User2 user2) {
        System.out.println("AbstractImpl2 2222222");
    }
}
