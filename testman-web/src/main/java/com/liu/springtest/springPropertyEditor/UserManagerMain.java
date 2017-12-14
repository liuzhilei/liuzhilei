package com.liu.springtest.springPropertyEditor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by liuzhilei on 2017/12/14.
 */
public class UserManagerMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-test.xml");
        UserManager userManager = (UserManager) context.getBean("userManager");
        System.out.println(userManager.getDataValue());
    }
}
