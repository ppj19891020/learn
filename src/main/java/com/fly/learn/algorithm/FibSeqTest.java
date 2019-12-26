package com.fly.learn.algorithm;

/**
 * 斐波那契数列：0,1,1,2,3,5,8,12....
 * f(n) = f(n-1)+f(n-2)
 * @author: peijiepang
 * @date 2019-12-26
 * @Description:
 */
public class FibSeqTest {

    /**
     * 利用递归方法，时间复杂度O(n^2)
     * @param n
     * @return
     */
    public static int test1(int n){
        if(n < 0){
            throw new RuntimeException("n不能为负数！！！");
        }
        if( n <= 1){
            return n;
        }
        return test1(n-1)+test1(n-2);
    }

    /**
     * 利用前置相加算法，时间复杂度O(n)
     * @param n
     * @return
     */
    public static int test2(int n){
        if(n < 0){
            throw new RuntimeException("n不能为负数！！！");
        }
        if( n <= 1){
            return n;
        }
        int before = 0;//表示第一个数
        int after = 1;//表示第二个数
        for(int i=0;i<n-1;i++){
            int restult = before + after;
            before = after;
            after = restult;
        }
        return after;
    }


    public static void main(String[] args) {
        System.out.println(test1(40));
        System.out.println(test2(40));
    }

}
