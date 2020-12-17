package com.fly.learn.algorithm.hashmap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 *
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 *
 * 示例1:
 *
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 * 示例 2:
 *
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 * 示例 3:
 *
 * 输入: pattern = "aaaa", str = "dog cat cat dog"
 * 输出: false
 * 示例 4:
 *
 * 输入: pattern = "abba", str = "dog dog dog dog"
 * 输出: false
 * 说明:
 * 你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。    
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2020/12/16
 * @Description:
 */
public class 单词规律 {

    /**
     *
     * @param pattern
     * @param s
     * @return
     */
    public boolean wordPattern(String pattern, String str) {
        Map<String, Character> str2ch = new HashMap<String, Character>();
        Map<Character, String> ch2str = new HashMap<Character, String>();
        int m = str.length();
        int i = 0;
        for (int p = 0; p < pattern.length(); ++p) {
            char ch = pattern.charAt(p);
            if (i >= m) {
                return false;
            }
            int j = i;
            while (j < m && str.charAt(j) != ' ') {
                j++;
            }
            String tmp = str.substring(i, j);
            if (str2ch.containsKey(tmp) && str2ch.get(tmp) != ch) {
                return false;
            }
            if (ch2str.containsKey(ch) && !tmp.equals(ch2str.get(ch))) {
                return false;
            }
            str2ch.put(tmp, ch);
            ch2str.put(ch, tmp);
            i = j + 1;
        }
        return i >= m;
    }

    public boolean wordPattern2(String pattern, String s) {
        String[] str = s.split(" ");
        if(pattern.length() != str.length){
            return false;
        }
        Map<Character,String> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        for(int i=0;i<pattern.length();i++){
            Character character = pattern.charAt(i);
            if(map.containsKey(character)){
                if(!map.get(character).equals(str[i])){
                    return false;
                }
            }else{
                if(set.contains(str[i])){
                    return false;
                }
                map.put(character,str[i]);
                set.add(str[i]);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String pattern = "abba";
        String str = "dog cat cat dog";
        单词规律 test = new 单词规律();
        test.wordPattern2(pattern,str);
    }

}
