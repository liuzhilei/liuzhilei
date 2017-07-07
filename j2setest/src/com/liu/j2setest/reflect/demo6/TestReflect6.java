package com.liu.j2setest.reflect.demo6;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by liuzhilei on 2017/7/7.
 * 获取某个类的全部方法，包括父类，接口和自身的方法
 */
public class TestReflect6 implements ReflectInterface {
    @Override
    public void test() {
        System.out.println("利用反射调用了TestReflect6的test方法");
    }

    public static void main(String[] args) throws Exception {
        Class<?> aClass = Class.forName("com.liu.j2setest.reflect.demo6.TestReflect6");
        //拿到所有方法
        Method[] methods = aClass.getMethods();
        for (int i = 0; i < methods.length; i++) {
            Class<?> returnType = methods[i].getReturnType();
            int modifiers = methods[i].getModifiers();
            Class<?>[] parameterTypes = methods[i].getParameterTypes();
            System.out.print(Modifier.toString(modifiers) + " ");//方法修饰符，即权限类型，包括 final static
            System.out.print(returnType.getName() + " ");//返回类型
            System.out.print(methods[i].getName() + "");//方法名字

            System.out.print("(");

            //拿到所有参数
            for (int j = 0; j < parameterTypes.length; j++) {
                System.out.print(parameterTypes[j].getName() + " arg" + j + ",");
            }
            System.out.print(")");

            //获取方法抛出的异常
            Class<?>[] exceptionTypes = methods[i].getExceptionTypes();
            if (exceptionTypes.length > 0) {
                System.out.print(" throw ");
                for (int x = 0; x < exceptionTypes.length; x++) {
                    //获取异常名
                    System.out.print(exceptionTypes[x].getName() + " ,");
                }
            }
            System.out.println();

        }

    }
}
