package com.liu.springtest.spring循环依赖;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by liuzhilei on 2017/9/22.
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("循环依赖/bean-circle.xml");
        A a = (A)context.getBean("a1");
        a.doHello();
    }
}
