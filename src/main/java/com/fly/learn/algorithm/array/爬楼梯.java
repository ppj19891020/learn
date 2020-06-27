package com.fly.learn.algorithm.array;

/**
 * 70
 //假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 //
 // 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 //
 // 注意：给定 n 是一个正整数。
 //
 // 示例 1：
 //
 // 输入： 2
 //输出： 2
 //解释： 有两种方法可以爬到楼顶。
 //1.  1 阶 + 1 阶
 //2.  2 阶
 //
 // 示例 2：
 //
 // 输入： 3
 //输出： 3
 //解释： 有三种方法可以爬到楼顶。
 //1.  1 阶 + 1 阶 + 1 阶
 //2.  1 阶 + 2 阶
 //3.  2 阶 + 1 阶
 //
 // Related Topics 动态规划
 //leetcode submit region begin(Prohibit modification and deletion)
 * @author: peijiepang
 * @date 2020/6/27
 * @Description:
 */
public class 爬楼梯 {

    /**
     * 动态规划
     * f(n)=f(n-1)+f(n-2)
     * @param n
     * @return
     */
    public static int climbStairs1(int n) {
        if(n<=1)
            return 1;
        int[] result = new int[n+1];
        result[1]=1;
        result[2]=2;
        for(int i=3;i<=n;i++){
            result[i] = result[i-1]+result[i-2];
        }
        return result[n];
    }

    /**
     * 斐波那契数列-递归
     * @param n
     * @return
     */
    public static int climbStairs2(int n) {
        if(n<=1)
            return 1;
        return climbStairs2(n-1)+climbStairs2(n-2);
    }

    public static void main(String[] args) {
        int test = 5;
        climbStairs1(test);
        climbStairs2(test);
    }

}
