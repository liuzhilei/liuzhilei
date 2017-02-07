package com.liu.springtest.lifecycletest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by liuzhilei on 2017/1/18.
 * spring允许在bean初始化之后，销毁之前执行特性的操作，常用的方式有三种：
 * 1.通过实现InitializingBean、DisposableBean接口来决定初始化之后、销毁之前的操作
 * 2.通过<bean>元素的init-method、destroy-method属性决定初始化之后，销毁之前的操作
 * 3.在指定方法上定义@PostConstruct和@PreDestroy决定初始化之后，销毁之前的操作
 *
 * 执行顺序：
 * construct
   postConstruct
   afterPropertiesSet
   init-method
 *
 * 验证上面三个的区别
 */
@Service
public class InitSequenceBean extends AbstractFactoryBean {

    public InitSequenceBean() {
        System.out.println("construct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }

    @Override
    public Class<?> getObjectType() {
        return InitSequenceBean.class;
    }

    @Override
    protected Object createInstance() throws Exception {
        return new InitSequenceBean();
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("postConstruct");
    }

    public void initMethod() {
        System.out.println("init-method");
    }

}
