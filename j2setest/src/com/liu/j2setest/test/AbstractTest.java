package com.liu.j2setest.test;

import com.liu.j2setest.liu.User;

/**
 * Created by liuzhilei on 2016/10/17.
 */
public abstract class AbstractTest<T extends User> implements AbstractInterface<T> {

    @Override
    public void service(T param){
        doBusiness(param);
    }

    protected abstract void doBusiness(T param);
}
