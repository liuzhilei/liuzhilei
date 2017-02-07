package com.liu.springtest.springbeanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.*;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;

/**
 * Created by liuzhilei on 2017/2/7.
 */
public class BeanLifeCycleService implements InitializingBean, DisposableBean, ApplicationContextAware,
        ApplicationEventPublisherAware, BeanClassLoaderAware, BeanFactoryAware,
        BeanNameAware, EnvironmentAware, ImportAware, ResourceLoaderAware {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("BeanLifeCycleService中利用set方法设置属性值");
        this.name = name;
    }

    public BeanLifeCycleService() {
        System.out.println("调用BeanLifeCycleService的默认无参构造方法");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("执行setApplicationContext，Bean Definition Names ="
                + Arrays.toString(applicationContext.getBeanDefinitionNames()));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        System.out.println("执行setApplicationEventPublisher");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("执行setBeanClassLoader, ClassLoader Name = " + classLoader.getClass().getName());
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("执行setBeanFactory，setBeanFactory bean 单例 = " + beanFactory.isSingleton("beanLifeCycleService"));
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("执行setBeanName，定义在上下文中的bean名称=" + name);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行DisposableBean接口的destroy方法");
    }

    //通过<bean>的destroy-method属性指定的销毁方法
    public void destroyMethod() throws Exception {
        System.out.println("执行配置的destroy-method");
    }

    //通过<bean>的init-method属性指定的初始化方法
    public void initMethod() throws Exception {
        System.out.println("执行配置的init-method");
    }

    @PostConstruct
    public void initPostConstruct(){
        System.out.println("执行PostConstruct注解标注的方法");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("执行preDestroy注解标注的方法");
    }

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("执行setEnvironment方法");
    }

    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        System.out.println("执行setImportMetadata方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行InitializingBean的afterPropertiesSet方法");
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        Resource resource = resourceLoader.getResource("classpath:spring-bean-life-cycle.xml");
        System.out.println("执行setResourceLoader: Resource File Name="
                + resource.getFilename());
    }
}
