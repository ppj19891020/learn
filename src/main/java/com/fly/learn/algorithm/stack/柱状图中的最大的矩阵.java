package com.fly.learn.algorithm.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 //给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 //
 // 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 //
 //
 //
 //
 //
 // 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 //
 //
 //
 //
 //
 // 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 //
 //
 //
 // 示例:
 //
 // 输入: [2,1,5,6,2,3]
 //输出: 10
 // Related Topics 栈 数组
 // https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
 * @author: peijiepang
 * @date 2020/7/8
 * @Description:
 */
public class 柱状图中的最大的矩阵 {

    /**
     * 暴力法，按照高度遍历，确认左边界和右边界来算出最大的面积
     * @param heights
     * @return
     */
    public static int largestRectangleAreas1(int[] heights) {
        int maxArea = 0;
        for(int i=0;i<heights.length;i++){
            // 左边界
            int left = i;
            // 右边界
            int right = i;
            int currentHeight = heights[i];
            while (left > 0 && heights[left-1] >= currentHeight){
                left--;
            }
            while (right < heights.length - 1  && heights[right] >= currentHeight){
                right ++;
            }
            maxArea = Math.max(maxArea,(right-left)*currentHeight);
        }
        return maxArea;
    }

    /**
     * 栈方式
     * @param heights
     * @return
     */
    public static int largestRectangleAreas2(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right,n);

        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<n;i++){
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
                right[stack.peek()] = i;
                stack.pop();
            }
            left[i] = stack.empty()?-1:stack.peek();
            stack.push(i);
        }
        int ans = 0;
        for(int i=0;i<n;++i){
            ans = Math.max(ans,(right[i]-left[i]-1)*heights[i]);
        }

        return ans;
    }



    public static void main(String[] args) {
        int[] test = new int[]{2,1,5,6,2,3};
        largestRectangleAreas1(test);
        largestRectangleAreas2(test);
    }

}
