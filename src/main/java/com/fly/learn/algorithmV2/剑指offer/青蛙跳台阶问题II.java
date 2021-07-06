package com.fly.learn.algorithmV2.剑指offer;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：n = 7
 * 输出：21
 * 示例 3：
 *
 * 输入：n = 0
 * 输出：1
 * 提示：
 *
 * 0 <= n <= 100
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2021/7/6
 * @Description:
 */
public class 青蛙跳台阶问题II {

    public int numWays(int n) {
        if(n<2){
            return 1;
        }
        int[] result = new int[n+1];
        result[0] = 1;
        result[1] = 1;
        for(int i= 2;i<=n;i++){
            result[i] = result[i-1] + result[i-2];
            result[i] = result[i] % 1000000007;
        }
        return result[n];
    }

}
