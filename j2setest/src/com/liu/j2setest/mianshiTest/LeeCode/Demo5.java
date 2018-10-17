package com.liu.j2setest.mianshiTest.LeeCode;

import java.util.*;

/**
 * Created by liuzhilei3 on 2018/9/19.
 * Given a string, find the length of the longest substring without repeating characters.
 * <p>
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * <p>
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * 获取最长不重复字符串的长度
 */
public class Demo5 {

    public static void main(String[] args) {
        //String str = "pwwkew";
        String str = "abba";
        //String str = "dvdf";
        //String str = "qrsvbspk";
        //String str = "abcabcbb";
        //String str = "ggububgvfk";
        //longestSubString1(str);
        longestSubString(str);

        System.out.println(test(str));

    }

    private static int longestSubString(String s) {
        if (s.length() == 0) return 0;
        int currentSize = 0;
        List<Character> list = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (list.contains(c)) {
                int x = list.indexOf(c);
                for (int j = 0; j <= x; j++) {
                    list.remove(0);
                }
            }
            if (!list.contains(c)) {
                list.add(c);
            }
            if (currentSize < list.size()) {
                currentSize = list.size();
            }
        }
        System.out.println(currentSize);
        return currentSize;
    }

    /**
     * 思路：定义两个指针i，j，在定义一个map
     * map中key为当前字符，value为当前位置
     * i就是for循环的当前值，j指的就是当遍历字符出现重复的时候，该数字在map中的位置，注意这个j获取的是当前字符出现的最大字符。当j重新赋值的时候，即是重新统计非重复字段的开始
     * 那么不重复字符串的长度就是i和j的差值的最大值，注意+1
     * @param s
     * @return
     */
    private static int test(String s) {
        if (s.length() == 0) return 0;
        Map<Character, Integer> map = new HashMap<>();
        int currentSize = 0;
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                j = Math.max(j, map.get(c));
            }
            map.put(c, i + 1);

            currentSize = Math.max(currentSize, (i + 1 - j));
        }
        return currentSize;
    }


}
