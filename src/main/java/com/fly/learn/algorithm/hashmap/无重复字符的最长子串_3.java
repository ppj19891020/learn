package com.fly.learn.algorithm.hashmap;

import java.util.HashMap;
import java.util.HashSet;
import org.apache.commons.math.stat.descriptive.rank.Max;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * @author: peijiepang
 * @date 2020-01-16
 * @Description:
 */
public class 无重复字符的最长子串_3 {

    private final static Logger LOGGER = LoggerFactory.getLogger(无重复字符的最长子串_3.class);

    /**
     * 滑动窗口法
     * 1. 定义[start,end]区间
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        int result = 0;//最长子串
        if(null == s || s.length() == 0){
            return result;
        }
        HashMap<Character,Integer> map = new HashMap<>();//临时用于保存是否重复字符
        for (int end = 0, start = 0; end < s.length(); end++) {
            if (map.containsKey(s.charAt(end))) {
                start = Math.max(map.get(s.charAt(end)), start);
            }
            result = Math.max(result, end - start + 1);
            map.put(s.charAt(end), end + 1);
        }
        LOGGER.info("字符串:{} 最大子串长度:{}",s,result);
        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        lengthOfLongestSubstring("abcabcbb");
        lengthOfLongestSubstring("bbbbb");
        lengthOfLongestSubstring("pwwkew");
        Thread.sleep(3000l);
    }

}
