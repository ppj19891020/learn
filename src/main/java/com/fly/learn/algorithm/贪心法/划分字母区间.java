package com.fly.learn.algorithm.贪心法;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：S = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 *  
 *
 * 提示：
 *
 * S的长度在[1, 500]之间。
 * S只包含小写字母 'a' 到 'z' 。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-labels
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2020/10/22
 * @Description:
 */
public class 划分字母区间 {

    /**
     * 贪心法+双指针
     * @param S
     * @return
     */
    public List<Integer> partitionLabels(String S) {
        List<Integer> result = new ArrayList<>();
        // 记录每个字符的最后偏移量
        int[] words = new int[26];
        for(int i=0;i<S.length();i++){
            words[S.charAt(i)-'a'] = i;
        }

        //双指针
        int start = 0,end = 0;
        for(int i=0;i<S.length();i++){
            end = Math.max(end,words[S.charAt(i)-'a']);
            if(i == end){
                result.add(end-start+1);
                start = end+1;
            }
        }
        return result;
    }
}
