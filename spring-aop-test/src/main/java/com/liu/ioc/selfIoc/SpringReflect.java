package com.liu.ioc.selfIoc;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhilei3 on 2018/6/26.
 * ioc是通过反射实现的
 */
public class SpringReflect {
    //类似spring的容器，存放反射得到的实例
    private List<Object> objectList;

    public SpringReflect() {
        objectList = new ArrayList<>();
    }

    public void reflectSetObjectList(Object object) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Class<?> aClass = object.getClass();
        if (aClass.isAnnotationPresent(MyService.class)) {
            //获取类的所有属性
            Field[] declaredFields = aClass.getDeclaredFields();

            for (Field field : declaredFields) {
                //判断属性是否添加了自定义注解
                if (field.isAnnotationPresent(MyAutoWired.class)) {
                    field.setAccessible(true);
                    Class<?> fieldClass = Class.forName(field.getType().getName(), false, Thread.currentThread().getContextClassLoader());
                    field.set(object, fieldClass.newInstance());
                    objectList.add(object);
                }
            }
        }
    }

    public List<Object> getObjectList() {
        return objectList;
    }
}
