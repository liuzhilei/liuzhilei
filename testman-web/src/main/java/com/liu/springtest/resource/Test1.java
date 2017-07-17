package com.liu.springtest.resource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Created by liuzhilei on 2017/7/17.
 *  获取项目中资源的两种方法
 */
public class Test1 {
    public static void main(String[] args) {
        Test1 test1 = new Test1();
        test1.test();
    }

    public void test() {
        try {
            String privatePath = this.getClass().getClassLoader().getResource("spring-bean-newinstance.xml").getPath();
            System.out.println("方法一获取路径:" + privatePath);

            Resource resource = new ClassPathResource("spring-bean-newinstance.xml");
            System.out.println("方法二获取路径:" + resource.getFile().getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
