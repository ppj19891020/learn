package com.fly.learn.algorithm;

/**
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 *
 *  
 *
 * 示例：
 *
 * 输入："Let's take LeetCode contest"
 * 输出："s'teL ekat edoCteeL tsetnoc"
 *  
 *
 * 提示：
 *
 * 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2020/8/30
 * @Description:
 */
public class 反转字符串中的单词 {

    /**
     * 双指针+单个字母替换
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        int left = 0; // 前指针
        int right = 0; // 后指针
        char[] temp = s.toCharArray();
        while (right <= temp.length){
            if(right != temp.length && temp[right] != ' '){
                right++;
            }else{
                //反转i-->j字符
                int next = right-- +1;//next是翻转完这个单词之后，left和right接下来要反转的单词的开始
                while (left < right){
                    char c = temp[left];
                    temp[left] = temp[right];
                    temp[right] = c;
                    left++;
                    right--;
                }
                left = next;
                right = next;
            }
        }
        return new String(temp);
    }

    public static void main(String[] args) {
        反转字符串中的单词 test = new 反转字符串中的单词();
        System.out.println(test.reverseWords("Let's take LeetCode contest"));
    }
}
