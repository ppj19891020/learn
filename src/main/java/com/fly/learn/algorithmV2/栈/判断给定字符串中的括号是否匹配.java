package com.fly.learn.algorithmV2.栈;

import com.alibaba.druid.sql.PagerUtils;
import com.alibaba.druid.sql.visitor.functions.Char;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author: peijiepang
 * @date 2021/4/30
 * @Description:
 */
public class 判断给定字符串中的括号是否匹配 {

    /**
     * 一般面试题目的描述都比较简单，在解答前，可以跟面试官进一步沟通一下题目要求和细节。以这道题为例，可以跟面试官确认括号的范围，是不是只考虑大中小括号就可以，包不包括尖括号；对函数的入参和返回值有没有什么样的要求；需不需要考虑针对大文件的操作等。
     *
     *  
     *
     * 我们假定细化后本题的要求为：只考虑大中小括号；不考虑针对大文件的操作，以字符串作为入参，返回值为布尔类型；未出现括号也算作匹配的情况。那么，解题思路如下。
     *
     * 字符匹配问题可以考虑使用栈的特性来处理。
     *
     * 遇到左括号时入栈，遇到右括号时出栈对比，看是不是成对的括号。
     *
     * 当匹配完成时，如果栈内为空说明匹配，否则说明左括号多于右括号。
     * @param str
     * @return
     */
    public static boolean isMatch(String str){
        char[] chars = str.toCharArray();
        Map<Character, Character> map = new HashMap<>();
        map.put('(',')');
        map.put('[',']');
        map.put('{','}');
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<chars.length;i++){
            char charValue = stack.peek().charValue();
            if(charValue == map.get(charValue)){
                // 刚好和栈匹配上，可以出栈
                stack.pop();
            } else {
                stack.push(chars[i]);
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {

    }

}
