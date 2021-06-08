package com.fly.learn.algorithmV2.剑指offer;

/**
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: n = 3
 * 输出: 6
 * 示例 2：
 *
 * 输入: n = 9
 * 输出: 45
 *  
 *
 * 限制：
 *
 * 1 <= n <= 10000
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/qiu-12n-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2021/5/26
 * @Description:
 */
public class 剑指offer64题 {

    /**
     * 短路递归思路：递归+回溯
     * Java 中，为构成语句，需加一个辅助布尔量 xx ，否则会报错；
     * Java 中，开启递归函数需改写为 sumNums(n - 1) > 0 ，此整体作为一个布尔量输出，否则会报错；
     * 初始化变量 resres 记录结果。（ Java 可使用第二栏的简洁写法，不用借助变量res）
     * @param n
     * @return
     */
    int res = 0;
    public int sumNums(int n) {
        // 终止条件
        boolean bol = n > 1 && (n+sumNums(n-1))>0;
        res += n;
        return res;
    }

}
