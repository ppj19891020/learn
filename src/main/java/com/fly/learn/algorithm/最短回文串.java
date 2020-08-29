package com.fly.learn.algorithm;

/**
 * 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
 *
 * 示例 1:
 *
 * 输入: "aacecaaa"
 * 输出: "aaacecaaa"
 * 示例 2:
 *
 * 输入: "abcd"
 * 输出: "dcbabcd"
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shortest-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2020/8/29
 * @Description:
 */
public class 最短回文串 {

    /**
     * kmp算法
     * @param s
     * @return
     */
    public String shortestPalindrome(String s) {
        if(s.length()==0){
            return s;
        }
        //使用反转字符串做为模板，原字符串作为匹配串
        int res=kmp(new StringBuilder(s).reverse().toString(),s);
        return new StringBuilder(s.substring(res)).reverse().toString()+s;
    }

    //kmp算法，返回匹配的最长字符串长度
    public static int kmp(String sStr, String dStr) {
        int sLen=sStr.length();
        int dLen=dStr.length();
        int i=0,j=0;
        int[] next=next(dStr);
        while(i<sLen && j<dLen){
            if(j==-1 || sStr.charAt(i)==dStr.charAt(j)){
                i++;
                j++;
            }else{
                j=next[j];
            }
        }
        return j;
    }

    //获得next数组
    public static int[] next(String dStr){
        int dLen=dStr.length();
        int[] next=new int[dLen];
        next[0] = -1;
        int i=0;
        int j=-1;
        while(i<dLen-1){
            if(j==-1 || dStr.charAt(i)==dStr.charAt(j)){
                ++i;
                ++j;
                next[i] = j;
            }else{
                j=next[j];
            }
        }
        return next;
    }

    public static void main(String[] args) {
        最短回文串 test = new 最短回文串();
        System.out.println(test.shortestPalindrome("aacecaaa"));
    }

}
