package com.fly.learn.algorithm.hashmap;

import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * @author: peijiepang
 * @date 2020-01-11
 * @Description:
 */
public class 两数之和 {

    private final static Logger LOGGER = LoggerFactory.getLogger(两数之和.class);

    /**
     * 求和
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        if(null == nums || nums.length <= 2){
            throw new RuntimeException("参数不匹配");
        }
        HashMap<Integer,Integer> temp = new HashMap<>(nums.length);
        for(int i=0;i<nums.length;i++){
            int num = nums[i];
            //结果需要的值
            int result = target - num;
            if(temp.get(result) != null){
                return new int[]{i,temp.get(result)};
            }else{
                temp.put(num,i);
            }
        }
        throw new RuntimeException("找不到最终值！");
    }

    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        int target = 9;
        LOGGER.info("result:{}",twoSum(nums,target));
    }

}
