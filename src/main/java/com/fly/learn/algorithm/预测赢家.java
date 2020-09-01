package com.fly.learn.algorithm;

/**
 * @author: peijiepang
 * @date 2020/9/1
 * @Description:
 */
public class 预测赢家 {
    public boolean PredictTheWinner(int[] nums) {
        //动态规划
        //dp[i][j] 代表i->j先手的比后手多的分数
        //当取左端num[i]时,dp[i][j] = nums[i] - dp[i+1][j]
        //当取右端num[j]时,dp[i][j] = nums[j] - dp[i][j-1]
        //当i == j时, dp[i][j] = num[i]
        int len = nums.length;
        int[][] dp = new int[len][len];
        for(int i = 0; i < len; i++) {
            dp[i][i] = nums[i];
        }

        for(int i = len-1; i >= 0; i--) {
            for(int j = i+1; j < len; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i+1][j], nums[j] - dp[i][j-1]);
            }
        }
        return dp[0][len-1] >= 0;
    }
}
