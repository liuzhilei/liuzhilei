package com.liu.springtest.newinstancebean;

/**
 * Created by liuzhilei on 2017/2/6.
 */
public class BeanInstance3Factory {

    public BeanInstance3Factory(){
        System.out.println("factory");
    }

    public BeanInstance3 createInstance(){
        System.out.println("使用实例工厂实例化");
        return new BeanInstance3();
    }

}
