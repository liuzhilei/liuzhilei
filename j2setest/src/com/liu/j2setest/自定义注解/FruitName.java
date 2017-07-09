package com.liu.j2setest.自定义注解;

import java.lang.annotation.*;

/**
 * Created by liuzhilei on 2017/7/9.
 * <p/>
 * 1.@Target 说明注解用在什么地方
 * ElementType.CONSTRUCTOR 描述构造器
 * ElementType.FIELD 描述域
 * ElementType.TYPE 描述类，接口或enum声明
 * ElementType.METHOD 描述方法
 * ElementType.PACKAGE 描述包
 * ElementType.PARAMETER 描述参数
 * </p>
 * 2.@Retention 表示需要在什么级别保存该注解信息
 * RetentionPolicy.SOURCE 源文件保留
 * RetentionPolicy.CLASS class保留
 * RetentionPolicy.RUNTIME 运行时保留
 * </p>
 * 3.@Documented 将注解包含在javaDoc中
 * </p>
 * 4.@Inherited 允许子类继承父类中的注解
 * <p/>
 * 自定义注解：
 * 定义注解格式：
 * 　　public @interface 注解名 {定义体}
 * 在定义注解时，不能继承其他的注解或接口。@interface用来声明一个注解，其中每一个方法实际是
 * 声明了一个配置参数。方法的名称就是参数的名称，返回类型就是参数的类型（只能是基本数据类型，
 * Class，String，enum类型），可以通过default设置参数的默认值。
 　　参数定义规定：
 　　　　1.只能使用public和默认两种修饰符
 　　　　2.参数类型只能使用八种基本数据类型，以及String,enum,Class,annotations等数据类型，或者他们的数组
 　　　　3.如果只有一个参数成员，最好把参数名称设置为value(),
 */

/**
 * 水果名称注解
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface FruitName {
    String value() default "aaa";
}
