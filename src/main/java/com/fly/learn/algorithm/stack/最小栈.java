package com.fly.learn.algorithm.stack;

import java.util.Stack;

/**
 * //设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * //
 * //
 * // push(x) —— 将元素 x 推入栈中。
 * // pop() —— 删除栈顶的元素。
 * // top() —— 获取栈顶元素。
 * // getMin() —— 检索栈中的最小元素。
 * //
 * //
 * //
 * //
 * // 示例:
 * //
 * // 输入：
 * //["MinStack","push","push","push","getMin","pop","top","getMin"]
 * //[[],[-2],[0],[-3],[],[],[],[]]
 * //
 * //输出：
 * //[null,null,null,null,-3,null,0,-2]
 * //
 * //解释：
 * //MinStack minStack = new MinStack();
 * //minStack.push(-2);
 * //minStack.push(0);
 * //minStack.push(-3);
 * //minStack.getMin();   --> 返回 -3.
 * //minStack.pop();
 * //minStack.top();      --> 返回 0.
 * //minStack.getMin();   --> 返回 -2.
 * //
 * //
 * //
 * //
 * // 提示：
 * //
 * //
 * // pop、top 和 getMin 操作总是在 非空栈 上调用。
 * //
 * // Related Topics 栈 设计
 * https://leetcode-cn.com/problems/min-stack/
 * @author: peijiepang
 * @date 2020/7/8
 * @Description:
 */
public class 最小栈 {

    static class MinStack {

        private Stack<Integer> data = new Stack<>();
        private Stack<Integer> help = new Stack<>();

        /** initialize your data structure here. */
        public MinStack() {

        }

        public void push(int x) {
            data.push(x);
            if(help.isEmpty() || help.peek() > x){
                help.push(x);
            }else{
                help.push(help.peek());
            }
        }

        public void pop() {
            if(data.isEmpty()){
                throw new RuntimeException("");
            }
            data.pop();
            help.pop();
        }

        public int top() {
            if(data.isEmpty()){
                throw new RuntimeException("");
            }
            return data.peek();
        }

        public int getMin() {
            if(help.isEmpty()){
                throw new RuntimeException("");
            }
            return help.peek();
        }
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin();  // --> 返回 -3.
        minStack.pop();
        minStack.top();      // --> 返回 0.
        minStack.getMin();   // --> 返回 -2.

    }

}
