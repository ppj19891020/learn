package com.fly.learn.algorithm.array;

import java.util.HashMap;
import java.util.Map;

/**
 //给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 //
 // 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 //
 //
 //
 // 示例:
 //
 // 给定 nums = [2, 7, 11, 15], target = 9
 //
 //因为 nums[0] + nums[1] = 2 + 7 = 9
 //所以返回 [0, 1]
 //
 // Related Topics 数组 哈希表
 * @author: peijiepang
 * @date 2020/6/29
 * @Description:
 */
public class 两数之和 {

    /**
     * 暴力破解法
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum1(int[] nums, int target) {
        for(int i=0;i<nums.length;i++){
            int temp = target - nums[i];
            for(int j=i+1;j<nums.length;j++){
                if(temp == nums[j]){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{};
    }

    /**
     * hash存储，二次遍历
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            map.put(nums[i],i);
        }
        for(int i=0;i<nums.length;i++){
            int temp = target - nums[i];
            if(map.containsKey(temp)){
                return new int[]{i,map.get(temp)};
            }
        }
        return new int[]{};
    }

    /**
     * hash一次遍历法
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum3(int[] nums,int target){
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int temp = target - nums[i];
            if(map.containsKey(temp)){
                return new int[]{i,map.get(temp)};
            }
            map.put(nums[i],i);
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        int[] test = new int[]{2, 10, 11, 7};
        System.out.println(twoSum1(test,9));
        System.out.println(twoSum2(test,9));
        System.out.println(twoSum3(test,9));
    }

}
