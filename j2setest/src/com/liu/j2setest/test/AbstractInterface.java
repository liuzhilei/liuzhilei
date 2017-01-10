package com.liu.j2setest.test;

import com.liu.j2setest.liu.User;

/**
 * Created by liuzhilei on 2016/10/17.
 */
public interface AbstractInterface<T extends User> {
    void service(T param);


}
