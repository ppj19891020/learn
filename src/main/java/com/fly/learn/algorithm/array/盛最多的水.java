package com.fly.learn.algorithm.array;

/**
 * leetcode 11
 //给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i,
 //ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 //
 // 说明：你不能倾斜容器，且 n 的值至少为 2。
 //
 //
 //
 //
 //
 // 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 //
 //
 //
 // 示例：
 //
 // 输入：[1,8,6,2,5,4,8,3,7]
 // 输出：49
 // Related Topics 数组 双指针
 * @author: peijiepang
 * @date 2020/6/27
 * @Description:
 */
public class 盛最多的水 {

    /**
     * 最普通的解法，直接用2个循环遍历计算面积，存储一个临时变量用于存储最大值
     * @param height
     * @return
     */
    public static int maxArea1(int[] height) {
        int max = 0;
        for(int i=0;i<height.length;i++){
            for(int j=1;j<height.length-1;j++){
                max = Math.max(max,Math.abs((i-j))*Math.min(height[i],height[j]));
            }
        }
        return max;
    }

    /**
     *  双指针法
     *  分别从左右指针开始计算，每次移动高度比较小的那个，然后保存临时最大值做比对，当i==j时候，此时得出最大值
     *  时间复杂度：O(n)
     *  空间复杂度：O(1)
     * @param height
     * @return
     */
    public static int maxArea2(int[] height) {
        int maxArea = 0;
        // i是左指针，从0开始
        int i = 0;
        // j是右指针，从最右边开始
        int j = height.length - 1;
        while(i < j){
            int area = Math.min(height[i],height[j]) * (j-i);
            maxArea = Math.max(area,maxArea);
            if(height[i] > height[j]){
                j--;
            }else{
                i++;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] test = new int[]{1,8,6,2,5,4,8,3,7};
        System.out.println("结果："+maxArea1(test));
        System.out.println("结果："+maxArea2(test));
    }

}
