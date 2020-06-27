package com.fly.learn.algorithm.array;

/**
 * @author: peijiepang
 * @date 2020/6/27
 * @Description:
 */
public class ArrayUtil {

    /**
     * 打印数组字符
     * @param nums
     * @return
     */
    public static String print(int[] nums){
        StringBuilder result = new StringBuilder();
        for(int i=0;i<nums.length;i++){
            result.append(nums[i]);
        }
        return result.toString();
    }

}
