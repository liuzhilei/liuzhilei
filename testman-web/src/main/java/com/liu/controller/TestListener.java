package com.liu.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by liuzhilei on 2017/2/6.
 * 实现ServletContextListener监听，可以自定义servlet启动的时候或者停止的时候需要做的操作。
 * spring容器的启动过程：
 * ContextLoaderListener监听ServletContext的初始化，ServletContext初始化以后实例化spring容器。销毁的时候spring容器先销毁，然后再销毁ServletContext容器。
 */
public class TestListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        System.out.println("context上下文 listener initialized ");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("context上下文 listener destroy ");
    }
}
