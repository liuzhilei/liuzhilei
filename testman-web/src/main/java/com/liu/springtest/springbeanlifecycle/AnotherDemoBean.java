package com.liu.springtest.springbeanlifecycle;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * Created by liuzhilei on 2017/1/19.
 */
public class AnotherDemoBean implements InitializingBean{

    @PostConstruct
    public void postConstruct(){
        System.out.println("AnotherDemoBean: postConstruct");
    }

    public void initMethod(){
        System.out.println("AnotherDemoBean: init-method");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("AnotherDemoBean: afterPropertiesSet");
    }
}
