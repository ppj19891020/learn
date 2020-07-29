package com.fly.learn.algorithm.滑动窗口;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 *
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 *
 * 示例1:
 *
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 *  
 *
 * 示例2:
 *
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 *  
 *
 * 注意：
 *
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutation-in-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2020/7/28
 * @Description:
 */
public class 字符串的排列 {

    public static boolean checkInclusion(String s1, String s2) {
        int[] needs = new int[26];
        for(Character character:s1.toCharArray()){
            needs[character-'a']++;
        }
        // 左右指针
        int left = 0;
        int right = 0;
        while (right < s2.length()){
            Character character = s2.charAt(right);
            right++;
            needs[character-'a']--;
            while (left < right && needs[character-'a']<0){
                needs[s2.charAt(left)-'a']++;
                left++;
            }
            if((right-left) == s1.length()){
                return true;
            }

        }
        return false;
    }

    public static void main(String[] args) {
        String s1 = "a",s2 = "ab";
        checkInclusion(s1,s2);
    }

}
