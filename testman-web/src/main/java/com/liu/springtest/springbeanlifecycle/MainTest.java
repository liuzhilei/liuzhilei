package com.liu.springtest.springbeanlifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by liuzhilei on 2017/1/19.
 * spring bean 生命周期测试
 */
public class MainTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-bean-lifecycle-test.xml");
    }
}
