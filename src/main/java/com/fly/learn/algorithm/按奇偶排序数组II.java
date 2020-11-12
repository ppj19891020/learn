package com.fly.learn.algorithm;

/**
 * @author: peijiepang
 * @date 2020/11/12
 * @Description:
 */
public class 按奇偶排序数组II {

    /**
     * 双指针，i,j分别表示奇数、偶数
     * @param A
     * @return
     */
    public int[] sortArrayByParityII(int[] A) {
        // i 表示偶数  j表示奇数
        int i = 0,j = 1;
        for(i = 0;i<A.length;i=i+2){
            if(A[i] % 2 != 0){
                //表示偶数为存的是奇数
                while (A[j] % 2 != 0){
                    j = j + 2;
                }
                swap(A,i,j);
            }
        }
        return A;
    }

    /**
     * 交换
     * @param num
     * @param i
     * @param j
     */
    public void swap(int[] num,int i,int j){
        int temp = num[j];
        num[j] = num[i];
        num[i] = temp;
    }


}
