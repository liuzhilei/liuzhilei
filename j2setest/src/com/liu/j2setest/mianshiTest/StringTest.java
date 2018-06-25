package com.liu.j2setest.mianshiTest;

/**
 * Created by liuzhilei3 on 2018/6/25.
 * replace没用到正则表达式，只是替换对应的字符
 * replaceAll，replaceFirst都用到了正则表达式
 * .匹配了出换行符以外的所有字符
 */
public class StringTest {
    public static void main(String[] args) {
        String s = "my.test.txt";
        //.只匹配的.这个字符
        System.out.println(s.replace(".", "#"));

        //匹配了所有的字符
        System.out.println(s.replaceAll(".", "#"));

        //通过\\将.转义，就可以只匹配.了
        System.out.println(s.replaceAll("\\.", "#"));

        //匹配的第一个字符
        System.out.println(s.replaceFirst(".", "#"));

        //通过\\将.转义，就可以只匹配第一个.了
        System.out.println(s.replaceFirst("\\.", "#"));

        //.匹配了所有字符，所以split长度为0
        String[] split = s.split(".");
        System.out.println(split.length);
        for(int i=0;i<split.length;i++){
            System.out.println(split[i]);
        }

        /**
         * 输出结果：
         my#test#txt
         ###########
         my#test#txt
         #y.test.txt
         my#test.txt
         0
         */
    }
}
