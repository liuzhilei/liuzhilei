package com.liu.springtest.springbeanlifecycle;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by liuzhilei on 2017/2/7.
 */
public class BeanTest {

    public static void main(String[] args) {
        System.out.println("Spring容器初始化");
        System.out.println("=====================================");

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-bean-life-cycle.xml");

        System.out.println("Spring容器初始化完毕");
        System.out.println("=====================================");

        System.out.println("从容器中获取bean");
        BeanLifeCycleService beanLifeCycleService = (BeanLifeCycleService)context.getBean("beanLifeCycleService");
        System.out.println("beanLifeCycleService name =" + beanLifeCycleService.getName());
        System.out.println("=====================================");

        context.close();

        System.out.println("spring容器关闭");
    }

    @Test
    public void beanTest(){
        System.out.println("Spring容器初始化");
        System.out.println("=====================================");

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-bean-life-cycle.xml");

        System.out.println("Spring容器初始化完毕");
        System.out.println("=====================================");

        System.out.println("从容器中获取bean");
        BeanLifeCycleService beanLifeCycleService = (BeanLifeCycleService)context.getBean("beanLifeCycleService");
        System.out.println("beanLifeCycleService name =" + beanLifeCycleService.getName());
        System.out.println("=====================================");

        context.close();

        System.out.println("spring容器关闭");

    }

}
