package com.liu.ioc.selfIoc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by liuzhilei3 on 2018/6/26.
 * 相当于spring的@Service
 */
@Target(ElementType.TYPE)//声明对类应用
@Retention(RetentionPolicy.RUNTIME)
public @interface MyService {
}
