package com.liu.j2setest.mianshiTest;

/**
 * Created by liuzhilei3 on 2018/8/15.
 * 字符串反转
 */
public class StringReverse {

    public static void main(String[] args) {
        String str = "abc def";

        System.out.println(reverse3(str));
    }

    private static String reverse1(String str) {
        String reverse = "";
        for (int i = 0; i < str.length(); i++) {
            reverse = str.charAt(i) + reverse;
        }

        return reverse;
    }

    private static String reverse2(String str) {
        String reverse = "";
        char[] chars = str.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            reverse += chars[i];
        }

        return reverse;
    }

    private static String reverse3(String str){
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder reverse = stringBuilder.append(str).reverse();
        return reverse.toString();
    }

}
