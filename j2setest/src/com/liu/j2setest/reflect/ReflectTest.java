package com.liu.j2setest.reflect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: liuzhilei
 * @Date: 2019/8/15
 * @Description: 多个重载的方法，反射调用
 */
public class ReflectTest {

    public static Method getMethod(Class classed, String methodName, List<Class> classList) {
        Method[] declaredMethods = classed.getDeclaredMethods();
        Method[] var4 = declaredMethods;
        int var5 = declaredMethods.length;

        for (int var6 = 0; var6 < var5; ++var6) {
            Method declaredMethod = var4[var6];
            if (declaredMethod.getName().equals(methodName)) {
                Class<?>[] parameterTypes = getParameterTypes(declaredMethod.getParameterTypes());
                List<Class<?>> classes = Arrays.asList(parameterTypes);
                if (!classes.equals(classList)) {
                    continue;
                }
                return declaredMethod;
            }
        }
        return null;
    }

    private static Class[] getParameterTypes(Class[] args) {
        if (args == null) {
            return null;
        }
        Class[] parameterTypes = new Class[args.length];
        for (int i = 0, j = args.length; i < j; i++) {
            String simpleName = args[i].getSimpleName();
            if (simpleName.equals("int")) {
                parameterTypes[i] = Integer.class;
            } else if (simpleName.equals("byte")) {
                parameterTypes[i] = Byte.class;
            } else if (simpleName.equals("short")) {
                parameterTypes[i] = Short.class;
            } else if (simpleName.equals("float")) {
                parameterTypes[i] = Float.class;
            } else if (simpleName.equals("double")) {
                parameterTypes[i] = Double.class;
            } else if (simpleName.equals("char")) {
                parameterTypes[i] = Character.class;
            } else if (simpleName.equals("long")) {
                parameterTypes[i] = Long.class;
            } else if (simpleName.equals("boolean")) {
                parameterTypes[i] = Boolean.class;
            } else {
                parameterTypes[i] = args[i].getClass();
            }
        }
        return parameterTypes;
    }

    public static void main(String[] args) throws Exception {
        List<Class> classList = new ArrayList<>();
        classList.add(Integer.class);
        classList.add(Long.class);
        Method method = getMethod(ReflectDemo.class, "test", classList);
        method.invoke(new ReflectDemo(), 1, 1L);
    }
}
