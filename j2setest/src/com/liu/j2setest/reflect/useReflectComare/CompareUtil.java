package com.liu.j2setest.reflect.useReflectComare;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by liuzhilei3 on 2018/8/13.
 * 利用反射实现对比两个实体是否一致，实体里面包含list集合，list集合也可以进行字段对比
 */
public class CompareUtil {

    private static boolean check(Class clazz, Object com1, Object com2) {
        try {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);

                Object com1Field = field.get(com1);
                Object com2Field = field.get(com2);
                if (com1Field == null && com2Field == null) {
                    continue;
                }

                if (com1Field == null || com2Field == null) {
                    System.out.println(clazz.getSimpleName() + ":" +field.getName() + ":" + com1Field);
                    System.out.println(clazz.getSimpleName() + ":" +field.getName() + ":" + com2Field);
                    continue;
                }

                if (field.getType() == List.class) {
                    // 如果是List类型，得到其Generic的类型
                    Type genericType = field.getGenericType();
                    if (genericType == null) continue;
                    // 如果是泛型参数的类型
                    if (genericType instanceof ParameterizedType) {
                        ParameterizedType pt = (ParameterizedType) genericType;
                        //得到泛型里的class类型对象
                        Class<?> genericClazz = (Class<?>) pt.getActualTypeArguments()[0];
                        List list1 = (List) com1Field;
                        List list2 = (List) com2Field;
                        if(list1.size() != list2.size()){
                            System.out.println("size 不一致"+clazz.getSimpleName() + ":" +field.getName() + ":" + com1Field);
                            System.out.println("size 不一致"+clazz.getSimpleName() + ":" +field.getName() + ":" + com2Field);
                            continue;
                        }

                        for (int i = 0; i < list1.size(); i++) {
                            check(genericClazz, ((List) com1Field).get(i), ((List) com2Field).get(i));
                        }
                    }
                }

                if (com1Field != com2Field && !com1Field.equals(com2Field)) {
                    if(com1Field instanceof List){
                        continue;
                    }
                    System.out.println(clazz.getSimpleName() + ":" +field.getName() + ":" + com1Field);
                    System.out.println(clazz.getSimpleName() + ":" +field.getName() + ":" + com2Field);
                    continue;
                }
                System.out.println("========");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

}
