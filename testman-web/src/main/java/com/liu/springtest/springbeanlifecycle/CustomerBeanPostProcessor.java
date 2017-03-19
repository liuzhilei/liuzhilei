package com.liu.springtest.springbeanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Created by liuzhilei on 2017/2/7.
 * <p/>
 * 如果我们需要在Spring容器完成Bean的实例化、配置和其他的初始化前后添加一些自己的逻辑处理，我
 * 们就可以定义一个或者多个BeanPostProcessor接口的实现，然后注册到容器中去。
 */
public class CustomerBeanPostProcessor implements BeanPostProcessor {
    /**
     * bean实例化之前进行的处理
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("bean创建之前，执行BeanPostProcessor的postProcessBeforeInitialization方法，beanName=" + beanName);
        return bean;
    }

    /**
     * bean实例化之后进行的处理
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("bean创建之后，执行BeanPostProcessor的postProcessAfterInitialization方法，beanName=" + beanName);
        return bean;
    }
}
