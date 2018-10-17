package com.liu.j2setest.mianshiTest.LeeCode;

import java.util.Stack;

/**
 * Created by liuzhilei3 on 2018/10/16.
 * 给定一个字符串，只包含'('，')'，'{'，'}'，'['和']'
 * 如果输入字符串有效：
 * 必须使用相同类型的括号关闭左括号。
 * 必须以正确的顺序关闭左括号。
 * 例如 （），（）[] {}，{[]}  都合法
 * （]，（[]] 不合法
 * 空字符串也被视为有效
 */
public class Demo6 {

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        /*
            {} 124 126
            [] 92 94
            () 41 42
         */
        System.out.println(isValid("([{}])"));
    }

}
