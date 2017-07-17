package com.liu.springtest.springbeanlifecycle;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

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
        /**
         * 获取bean第一种方式
         */
        BeanLifeCycleService beanLifeCycleService = (BeanLifeCycleService)context.getBean("beanLifeCycleService");
        System.out.println("beanLifeCycleService name =" + beanLifeCycleService.getName());

        /*
        * 获取bean第二种方式，返回值为map，key为类的名字，首字母小写
        * */
        /*Map<String, BeanLifeCycleService> beansOfType = context.getBeansOfType(BeanLifeCycleService.class);
        System.out.println("BeanLifeCycleService: " + beansOfType.get("beanLifeCycleService"));*/

        /**
         * 获取bean第三种方式
         */
        //BeanLifeCycleService beanLifeCycleService1 = context.getBean("beanLifeCycleService", BeanLifeCycleService.class);


        System.out.println("=====================================");

        context.close();

        System.out.println("spring容器关闭");
    }

    @Test
    public void beanTest() {
        System.out.println("Spring容器初始化");
        System.out.println("=====================================");

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-bean-life-cycle.xml");

        System.out.println("Spring容器初始化完毕");
        System.out.println("=====================================");

        System.out.println("从容器中获取bean");
        BeanLifeCycleService beanLifeCycleService = (BeanLifeCycleService) context.getBean("beanLifeCycleService");
        System.out.println("beanLifeCycleService name =" + beanLifeCycleService.getName());
        System.out.println("=====================================");

        context.close();

        System.out.println("spring容器关闭");

    }

}
