package com.liu.proxy.cglib;

/**
 * Created by liuzhilei on 2017/1/17.
 */
public class CglibProgrammer {

    public CglibProgrammer(){
        code1();
    }

    public void code() {
        System.out.println("cglib动态代理，运行期织入，生成的class是继承这个类的子类");
    }

    public void code1(){
        System.out.println("调用了code1方法");
    }

}
