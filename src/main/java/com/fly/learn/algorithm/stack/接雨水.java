package com.fly.learn.algorithm.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 //给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 //
 //
 //
 // 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Mar
 //cos 贡献此图。
 //
 // 示例:
 //
 // 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 //输出: 6
 // Related Topics 栈 数组 双指针
 //https://leetcode-cn.com/problems/trapping-rain-water/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-8/
 * @author: peijiepang
 * @date 2020/7/10
 * @Description:
 */
public class 接雨水 {

    /**
     * 大顶栈
     * 总体的原则就是，
     *
     * 当前高度小于等于栈顶高度，入栈，指针后移。
     *
     * 当前高度大于栈顶高度，出栈，计算出当前墙和栈顶的墙之间水的多少，然后计算当前的高度和新栈的高度的关系，重复第 2 步。直到当前墙的高度不大于栈顶高度或者栈空，然后把当前墙入栈，指针后移。
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        int res = 0;
        int current = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        while (current < height.length){
            //如果栈不空并且当前指向的高度大于栈顶高度就一直循环
            while (!stack.isEmpty() && height[stack.peek()] < height[current]){
                int top = stack.peek();//取出要出栈的元素
                stack.pop();//出栈
                if(stack.isEmpty()){ // 栈空就出去
                    break;
                }
                int distance = current - stack.peek() - 1; //两堵墙之前的距离
                int bounded_height = Math.min(height[current],height[stack.peek()]) - height[top];
                res += distance * bounded_height;
            }
            stack.push(current++);//当前指向的墙入栈
        }
        return res;
    }

    public static void main(String[] args) {
        int[] test = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        trap(test);
    }

}
