package com.fly.learn.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
 *
 * 你可以按任意顺序返回答案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：["bella","label","roller"]
 * 输出：["e","l","l"]
 * 示例 2：
 *
 * 输入：["cool","lock","cook"]
 * 输出：["c","o"]
 *  
 *
 * 提示：
 *
 * 1 <= A.length <= 100
 * 1 <= A[i].length <= 100
 * A[i][j] 是小写字母
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-common-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2020/10/14
 * @Description:
 */
public class 查找常用字符 {

    public List<String> commonChars(String[] A) {
        List<String> result = new ArrayList<>();
        int[] minfreq = new int[26];
        Arrays.fill(minfreq, Integer.MAX_VALUE);
        for(String word:A){
            int[] freq = new int[26];
            for(int i=0;i<word.length();i++){
                char temp = word.charAt(i);
                freq[temp-'a']++;
            }
            for(int i=0;i<26;i++){
                minfreq[i] = Math.min(minfreq[i],freq[i]);
            }
        }

        for(int i=0;i<26;i++){
            for(int j=0;j<minfreq[i];j++){
                result.add(String.valueOf((char)(i+'a')));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        查找常用字符 temp = new 查找常用字符();
        temp.commonChars(new String[]{"cool","lock","cook"});
    }

}
