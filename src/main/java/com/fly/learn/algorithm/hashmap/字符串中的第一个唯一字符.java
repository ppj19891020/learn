package com.fly.learn.algorithm.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 *  
 *
 * 示例：
 *
 * s = "leetcode"
 * 返回 0
 *
 * s = "loveleetcode"
 * 返回 2
 *  
 *
 * 提示：你可以假定该字符串只包含小写字母。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-unique-character-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2020/12/23
 * @Description:
 */
public class 字符串中的第一个唯一字符 {

    /**
     * 数组缓存
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        // 首次位置
        Map<Character,Integer> position = new HashMap<>();
        for(int i=0;i<s.length();i++){
            Character temp = s.charAt(i);
            if(position.containsKey(temp)){
                position.put(temp,-1);
            }else{
                position.put(temp,i);
            }
        }
        int first = s.length();
        for(Map.Entry<Character,Integer> entry:position.entrySet()){
            int pos = entry.getValue();
            if(pos != -1 && pos < first){
                first = pos;
            }
        }
        if (first == s.length()) {
            first = -1;
        }
        return first;
    }

    public static void main(String[] args) {
        字符串中的第一个唯一字符 test = new 字符串中的第一个唯一字符();
        test.firstUniqChar("leetcode");
    }

}
