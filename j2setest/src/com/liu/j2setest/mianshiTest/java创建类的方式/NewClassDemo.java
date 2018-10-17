package com.liu.j2setest.mianshiTest.java创建类的方式;

import java.io.Serializable;

/**
 * Created by liuzhilei3 on 2018/8/20.
 */
public class NewClassDemo implements Serializable, Cloneable {

    public NewClassDemo(){
        System.out.println("构造方法");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
