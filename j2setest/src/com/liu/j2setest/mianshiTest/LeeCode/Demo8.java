package com.liu.j2setest.mianshiTest.LeeCode;

/**
 * Created by liuzhilei3 on 2018/10/16.
 * 获取字符串数组中，相同前缀的最大长度
 */
public class Demo8 {
    public static void main(String[] args) {
        String str = "abc";
        String strs [] = {"aca","cba"};
        String strs1 [] = {"flower","flow","flight"};
        System.out.println(longestCommonPrefix(strs));
    }
    public static String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0) return "";
        int minLen = strs[0].length();
        for (int i = 0; i < strs.length; i++) {
            minLen = Math.min(minLen, strs[i].length());
        }

        int len = 0;
        for (int i = 0; i < minLen; i++) {
            boolean isEquals = true;
            for (int j = 0; j < strs.length - 1; j++) {
                if (strs[j].charAt(i) != strs[j + 1].charAt(i)) {
                    if(i == 0){
                        return "";
                    }
                    isEquals = false;
                }
            }
            if(isEquals) len++;
        }

        return len == 0 ? "" : strs[0].substring(0,len);
    }
}
