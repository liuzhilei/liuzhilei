package com.liu.springtest.newinstancebean;

import javax.annotation.PostConstruct;

/**
 * Created by liuzhilei on 2017/2/6.
 */
public class BeanInstance2 {

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void sayHello() {
        System.out.println("哈喽" + msg);
    }

    public static BeanInstance2 createInstance(){
        System.out.println("静态工厂方法实例化");
        return new BeanInstance2();
    }

}
