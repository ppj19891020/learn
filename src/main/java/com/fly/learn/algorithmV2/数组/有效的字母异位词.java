package com.fly.learn.algorithmV2.数组;

/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词(字母数量相同，顺序不同)。
 *
 * 示例 1:
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 *
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-anagram
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2021/4/2
 * @Description:
 */
public class 有效的字母异位词 {

    /**
     * 1. 只包括小写字符，用一个int[26]数组；
     * 2. 一次遍历每个字母的数量，查看是否相等即可
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        int[] nums = new int[26];
        for(int i=0;i<s.length();i++){
            int index = s.charAt(i) - 'a';
            nums[index]++;
        }
        for (int i=0;i<t.length();i++){
            int index = t.charAt(i) - 'a';
            nums[index]--;
        }
        for(int i = 0;i < nums.length;i++){
            if(nums[i] != 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        有效的字母异位词 test = new 有效的字母异位词();
        test.isAnagram("a","ab");
    }

}
