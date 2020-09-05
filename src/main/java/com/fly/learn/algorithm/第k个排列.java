package com.fly.learn.algorithm;

import java.util.Arrays;

/**
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 *
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 *
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 *
 * 说明：
 *
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 * 示例 1:
 *
 * 输入: n = 3, k = 3
 * 输出: "213"
 * 示例 2:
 *
 * 输入: n = 4, k = 9
 * 输出: "2314"
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutation-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2020/9/5
 * @Description:
 */
public class 第k个排列 {

    // 阶乘
    private int[] factorial;

    private boolean[] used;

    private int n;
    private int k;

    public String getPermutation(int n, int k) {
        this.n = n;
        this.k = k;

        // 计算阶乘数组
        factorial = new int[n+1];
        factorial[0] = 1;
        for(int i=1;i<n+1;i++){
            factorial[i]=factorial[i-1]*i;
        }

        //查找全排列需要的布尔数组
        used = new boolean[n+1];
        Arrays.fill(used,false);

        StringBuilder path = new StringBuilder();
        // 回溯法
        dfs(0,path);
        return path.toString();
    }

    private void dfs(int index, StringBuilder path) {
        if(n == index){
            return;
        }
        // 计算还未确定的数字的全排列的个数，第 1 次进入的时候是 n - 1
        int cnt = factorial[n - 1 - index];
        for(int i=1;i<=n;i++){
            if(used[i]){
                continue;
            }
            if(cnt < k){
                k -= cnt;
                continue;
            }
            path.append(i);
            used[i]=true;
            dfs(index+1,path);
            return;
        }
    }

    public static void main(String[] args) {
        第k个排列 test = new 第k个排列();
        test.getPermutation(3,3);
    }

}
