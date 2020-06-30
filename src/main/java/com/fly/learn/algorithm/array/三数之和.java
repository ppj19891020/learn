package com.fly.learn.algorithm.array;

import java.util.ArrayList;
import java.util.List;

/**
 //给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复
 //的三元组。
 //
 // 注意：答案中不可以包含重复的三元组。
 //
 //
 //
 // 示例：
 //
 // 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 //
 //满足要求的三元组集合为：
 //[
 //  [-1, 0, 1],
 //  [-1, -1, 2]
 //]
 //
 // Related Topics 数组 双指针
 * @author: peijiepang
 * @date 2020/6/29
 * @Description:
 */
public class 三数之和 {

    /**
     * 暴力破解法，三重循环
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for(int i=0;i<nums.length-2;i++){
            for(int j=i+1;j<nums.length-1;j++){
                int temp = 0 - nums[i] - nums[j];
                for(int k=j+1;k<nums.length;k++){
                    if(temp == nums[k]){
                        List<Integer> r = new ArrayList<>();
                        r.add(nums[i]);
                        r.add(nums[j]);
                        r.add(nums[k]);
                        result.add(r);
                    }
                }
            }
        }
        return result;
    }

    /**
     * 双指正法
     * 1. 前提需要将数组排序，从小 ---> 大
     * 2.
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum2(int[] nums){
        sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        // 左指针
        int left;
        // 右指针
        int right;
        for(int i=0;i<nums.length;i++){
            left = i+1;
            right = nums.length-1;
            while (left < right){
                if(nums[left]+nums[right]+nums[i] == 0){
                    List<Integer> res = new ArrayList<>();
                    res.add(nums[left]);
                    res.add(nums[right]);
                    res.add(nums[i]);
                    result.add(res);
                    break;
                }else if(nums[left]+nums[right]+nums[i]>0){
                    right --;
                }else if(nums[left]+nums[right]+nums[i]<0){
                    left ++;
                }
            }
        }
        return result;
    }

    /**
     * 选择排序
     * @param nums
     */
    public static void sort(int[] nums){
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i] > nums[j]){
                    int temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] test = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum1(test));
        System.out.println(threeSum2(test));
    }


}
