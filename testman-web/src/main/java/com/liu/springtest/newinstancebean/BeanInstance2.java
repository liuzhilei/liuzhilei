package com.liu.springtest.newinstancebean;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by liuzhilei on 2017/2/6.
 */
public class BeanInstance2 {

    private String msg;
    private List<Integer> list;
    private boolean flag;

    public void sayHello() {
        System.out.println("哈喽" + msg);
        System.out.println("list:" + list);
        System.out.println("flag:" + flag);
    }

    public static BeanInstance2 createInstance(String message) {
        System.out.println("静态工厂方法实例化");
        System.out.println("message:" + message);
        return new BeanInstance2();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
