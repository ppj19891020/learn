package com.fly.learn.algorithm.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 //给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 //
 // 有效字符串需满足：
 //
 //
 // 左括号必须用相同类型的右括号闭合。
 // 左括号必须以正确的顺序闭合。
 //
 //
 // 注意空字符串可被认为是有效字符串。
 //
 // 示例 1:
 //
 // 输入: "()"
 //输出: true
 //
 //
 // 示例 2:
 //
 // 输入: "()[]{}"
 //输出: true
 //
 //
 // 示例 3:
 //
 // 输入: "(]"
 //输出: false
 //
 //
 // 示例 4:
 //
 // 输入: "([)]"
 //输出: false
 //
 //
 // 示例 5:
 //
 // 输入: "{[]}"
 //输出: true
 // Related Topics 栈 字符串
 // https://leetcode-cn.com/problems/valid-parentheses/
 * @author: peijiepang
 * @date 2020/7/8
 * @Description:
 */
public class 有效的括号 {

    /**
     * stack 方式
     * 将左括号或者做左方括号放入stack中，如果遇到右括号直接与stack中对应则出栈，直接stack中没有元素，则是通过
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        Map<Character,Character> map = new HashMap<>();
        map.put(')','(');
        map.put(']','[');
        map.put('}','{');

        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for(char c:chars){
            if(map.containsKey(c)){
                if(stack.empty()){
                    return false;
                }
                Character temp = stack.peek();
                if(temp.charValue() == map.get(c)){
                    stack.pop();
                }else{
                    return false;
                }
            }else{
                stack.push(c);
            }
        }
        if(stack.empty()){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isValid("][()"));
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("([)]"));
        System.out.println(isValid("{[]}"));
    }


}
