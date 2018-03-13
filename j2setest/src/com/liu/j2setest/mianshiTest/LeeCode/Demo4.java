package com.liu.j2setest.mianshiTest.LeeCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuzhilei on 2018/2/27.
 * 给定一个字符串，找到最长的子字符串的长度而不重复字符。
 * <p/>
 * 例子：
 * 给定"abcabcbb"的答案是"abc"，长度是3。
 * 给定"bbbbb"的答案是"b"，长度为1。
 * 给定"pwwkew"的答案是"wke"，长度为3.请注意，答案必须是一个子字符串，"pwke"是一个子序列，而不是一个子字符串。
 * <p/>
 * 思路：
 * 定义一个map，key存放字符串每一个字符，value存放字符所在的位置。定义两个只会向前移动的指针。
 * todo 还是没有太明白
 * 参考leecode：
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 *
 */
public class Demo4 {

    public static void main(String[] args) {
        String str = "abcabcbb";
        //System.out.println(str.charAt(0));
        lengthOfLongestSubstring(str);
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        for (int i = 0, j = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                j = Math.max(j, map.get(s.charAt(i)) + 1); //保证了j只会向前移动
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - j + 1);
            System.out.println(max);
        }
        System.out.println(max);
        return max;
    }
}
