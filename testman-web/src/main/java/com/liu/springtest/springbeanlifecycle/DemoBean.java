package com.liu.springtest.springbeanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by liuzhilei on 2017/1/19.
 */
@Component
public class DemoBean implements BeanNameAware, BeanFactoryAware, InitializingBean, BeanPostProcessor, DisposableBean {

    @PostConstruct
    public void postConstruct() {
        System.out.println("demoBean: postConstruct");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("demoBean: BeanFactoryAware, [beanFactory=" + beanFactory.toString() + "]");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("demoBean: BeanNameAware, [name=" + name + "]");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("demoBean: DisposableBean, destroy method");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("demoBean: afterPropertiesSet");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("demoBean: BeanPostProcessor, postProcessBeforeInitialization[beanName=" + beanName + ",bean=" + bean + "]");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("demoBean: BeanPostProcessor, postProcessAfterInitialization[beanName=" + beanName + ",bean=" + bean + "]");
        return bean;
    }

    public void initMethod(){
        System.out.println("demoBean: init-method");
    }

}
