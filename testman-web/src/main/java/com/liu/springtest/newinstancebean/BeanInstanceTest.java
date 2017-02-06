package com.liu.springtest.newinstancebean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by liuzhilei on 2017/2/6.
 */
public class BeanInstanceTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-bean-newinstance.xml");

        System.out.println("--------------------");
        BeanInstance2 beanInstance2 = (BeanInstance2)applicationContext.getBean("beanInstance2");
        beanInstance2.sayHello();

        /*System.out.println("--------------------");
        BeanInstance3 beanInstance3 = (BeanInstance3)applicationContext.getBean("beanInstance3");
        beanInstance3.sayHello();*/

    }
}
