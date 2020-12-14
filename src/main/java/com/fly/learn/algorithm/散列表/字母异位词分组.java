package com.fly.learn.algorithm.散列表;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 示例:
 *
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * 说明：
 *
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/group-anagrams
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2020/12/14
 * @Description:
 */
public class 字母异位词分组 {

    /**
     * 计数法
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> result = new HashMap<>();
        for(String str:strs){
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> list = result.getOrDefault(key,new ArrayList<>());
            list.add(str);
            result.put(key,list);
        }
        return new ArrayList<>(result.values());
    }

    /**
     * 计数
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String,List<String>> result = new HashMap<>();
        for(String str:strs){
            int[] temp = new int[26];
            char[] chars = str.toCharArray();
            for(char c:chars){
                temp[c-'a']++;
            }
            StringBuilder stringBuilder = new StringBuilder();
            for(int i=0;i<temp.length;i++){
                stringBuilder.append(((char)i+'a')).append(temp[i]);
            }
            String key = stringBuilder.toString();
            List<String> list = result.getOrDefault(key,new ArrayList<>());
            list.add(str);
            result.put(key,list);
        }
        return new ArrayList<>(result.values());
    }

    public static void main(String[] args) {
        args = new String[]{"eat","tea","tan","ate","nat","bat"};
        字母异位词分组 test = new 字母异位词分组();
        test.groupAnagrams2(args);
    }

}
