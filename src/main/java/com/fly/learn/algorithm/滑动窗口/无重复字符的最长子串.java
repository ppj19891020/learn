package com.fly.learn.algorithm.滑动窗口;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
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
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2020/7/28
 * @Description:
 */
public class 无重复字符的最长子串 {

    public static int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<>();
        // 右指针
        int right = 0;
        // 结果
        int res = 0;
        for(int left = 0;left<s.length();left++){
            // 左指针向右移动一格，移除一个字符
            if(left>0){
                occ.remove(s.charAt(left-1));
            }
            while (right < s.length() && !occ.contains(s.charAt(right))){
                occ.add(s.charAt(right));
                right++;
            }
            res = Math.max(res,right-left);
        }
        return res;
    }

    public static void main(String[] args) {
        String test = "pwwkew";
        lengthOfLongestSubstring(test);
    }

}
