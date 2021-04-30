package com.fly.learn.algorithmV2.动态规划;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * 示例 2：
 *
 * 输入：nums = [1]
 * 输出：1
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：0
 * 示例 4：
 *
 * 输入：nums = [-1]
 * 输出：-1
 * 示例 5：
 *
 * 输入：nums = [-100000]
 * 输出：-100000
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 104
 * -105 <= nums[i] <= 105
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2021/4/30
 * @Description:
 */
public class 最大子序和 {

    /**
     * 动态规划
     * 动态转移方程：dp[i] 以nums[i]为尾结点的最大子序子和
     * 如果dp[i-1]> 0,则dp[i] = dp[i-1] + nums[i]；
     * 如果dp[i-1] < 0,则dp[i] = nums[i]
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        int result = 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for(int i=1;i<nums.length;i++){
            if(dp[i-1] > 0){
                dp[i] = dp[i-1] + nums[i];
            }else {
                dp[i] = nums[i];
            }
            result = Math.max(result,dp[i]);
        }
        return result;
    }

}
