package com.fly.learn.algorithm.滑动窗口;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。
 *
 * 示例：
 *
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 *
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-window-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2020/7/22
 * @Description:
 */
public class 最小覆盖子串 {

    /**
     * 滑动窗口
     * @param s
     * @param t
     * @return
     */
    public static String minWindow(String s, String t) {
        if(s == null || s == "" || t == null || t == "" || s.length() < t.length()){
            return "";
        }
        // 用来统计t中每个字符的个数
        int[] needs = new int[128];
        // 用来统计滑动窗口中每个字符出现的次数
        int[] window = new int[128];
        for(int i=0;i<t.length();i++){
            needs[t.charAt(i)]++;
        }

        // 结果值
        String res = "";
        // 左指针、右指针
        int left = 0 ;
        int right = 0;

        // 目前多少个字符
        int count = 0;
        // 用来记录最短需要多个个字符
        int minLength = s.length() + 1;
        while (right < s.length()){
            char ch = s.charAt(right);
            window[ch]++;
            if(needs[ch] > 0 && needs[ch] >= window[ch]){
                count++;
            }
            // 移动到不满足条件为止
            while(count == t.length()){
                ch = s.charAt(left);
                if(needs[ch] > 0 && needs[ch] >= window[ch]){
                    count--;
                }
                if(right - left + 1 < minLength){
                    minLength = right - left + 1;
                    res = s.substring(left,right+1);
                }
                window[ch]--;
                left++;
            }
            right++;
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        minWindow(s,t);
    }

}
