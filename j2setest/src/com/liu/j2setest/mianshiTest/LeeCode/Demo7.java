package com.liu.j2setest.mianshiTest.LeeCode;

/**
 * Created by liuzhilei3 on 2018/10/16.
 * 罗马数字是由七个不同的符号来表示  I，V，X，L，C，D和M。
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * <p>
 * 特例为：
 * I可以在V（5）和X（10）之前放置4和9。
 * X可以在L（50）和C（100）之前放置40和90。
 * C可以在D（500）和M（1000）之前放置以产生400和900。
 */
public class Demo7 {
    public static void main(String[] args) {
        System.out.println(romanToInt("MCMXCIV"));
        System.out.println(romanToInt2("MCMXCIV"));
    }

    public static int romanToInt2(String s) {
        int[] nums = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'I':
                    nums[i] = 1;
                    break;
                case 'V':
                    nums[i] = 5;
                    break;
                case 'X':
                    nums[i] = 10;
                    break;
                case 'L':
                    nums[i] = 50;
                    break;
                case 'C':
                    nums[i] = 100;
                    break;
                case 'D':
                    nums[i] = 500;
                    break;
                case 'M':
                    nums[i] = 1000;
                    break;
            }
        }
        int num = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                num -= nums[i];
            } else {
                num += nums[i];
            }
        }
        num = num + nums[nums.length-1];
        return num;
    }


    public static int romanToInt(String s) {
        char[] chars = s.toCharArray();
        int t = 0;
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == 'I') {
                if (chars[i + 1] == 'V') {
                    t += 4;
                    chars[i] = '0';
                    chars[i + 1] = '0';
                } else if (chars[i + 1] == 'X') {
                    t += 9;
                    chars[i] = '0';
                    chars[i + 1] = '0';
                }

            } else if (chars[i] == 'X') {
                if (chars[i + 1] == 'L') {
                    t += 40;
                    chars[i] = '0';
                    chars[i + 1] = '0';
                } else if (chars[i + 1] == 'C') {
                    t += 90;
                    chars[i] = '0';
                    chars[i + 1] = '0';
                }
            } else if (chars[i] == 'C') {
                if (chars[i + 1] == 'D') {
                    t += 400;
                    chars[i] = '0';
                    chars[i + 1] = '0';
                } else if (chars[i + 1] == 'M') {
                    t += 900;
                    chars[i] = '0';
                    chars[i + 1] = '0';
                }
            }
        }
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'I') t += 1;
            if (chars[i] == 'V') t += 5;
            if (chars[i] == 'X') t += 10;
            if (chars[i] == 'L') t += 50;
            if (chars[i] == 'C') t += 100;
            if (chars[i] == 'D') t += 500;
            if (chars[i] == 'M') t += 1000;
        }
        return t;
    }
}
