package com.fly.learn.algorithm;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
 *
 * 注意：如果对空文本输入退格字符，文本继续为空。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：S = "ab#c", T = "ad#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “ac”。
 * 示例 2：
 *
 * 输入：S = "ab##", T = "c#d#"
 * 输出：true
 * 解释：S 和 T 都会变成 “”。
 * 示例 3：
 *
 * 输入：S = "a##c", T = "#a#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “c”。
 * 示例 4：
 *
 * 输入：S = "a#c", T = "b"
 * 输出：false
 * 解释：S 会变成 “c”，但 T 仍然是 “b”。
 *  
 *
 * 提示：
 *
 * 1 <= S.length <= 200
 * 1 <= T.length <= 200
 * S 和 T 只含有小写字母以及字符 '#'。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/backspace-string-compare
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2020/10/19
 * @Description:
 */
public class 比较含退格的字符串 {

    /**
     * 栈方法
     * @param S
     * @param T
     * @return
     */
    public boolean backspaceCompare(String S, String T) {
        return conver(S).equals(conver(T));
    }

    private String conver(String str){
        LinkedList<Character> stack = new LinkedList<>();
        for(int i=0;i<str.length();i++){
            char temp = str.charAt(i);
            if('#' == temp){
                stack.poll();
            }else{
                stack.push(str.charAt(i));
            }
        }
        Iterator<Character> iterator = stack.iterator();
        StringBuilder builder = new StringBuilder();
        while (iterator.hasNext()){
            builder.append(iterator.next());
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String S = "ab#c", T = "ad#c";
        比较含退格的字符串 test = new 比较含退格的字符串();
        test.backspaceCompare(S,T);
    }

}
