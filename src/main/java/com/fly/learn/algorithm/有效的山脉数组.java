package com.fly.learn.algorithm;

/**
 * @author: peijiepang
 * @date 2020/11/3
 * @Description:
 */
public class 有效的山脉数组 {

    /**
     * 从小到大找到最大值，然后从最大值开始找，一直要找到最小值，判断最小值是否是最后一个数字
     * @param A
     * @return
     */
    public boolean validMountainArray(int[] A) {
        int i = 0;
        while (i<A.length && i+1<A.length && A[i] < A[i+1]){
            i++;
        }
        if(i == 0 || i == A.length - 1){
            return false;
        }
        while (i < A.length && i+1 < A.length && A[i] > A[i+1]){
            i++;
        }
        if(i == A.length-1){
           return true;
        }
        return false;
    }

    /**
     * 双指针，判断左右指针是否是同一个即可
     * @param A
     * @return
     */
    public boolean validMountainArray1(int[] A) {
        // 左右指针
        int left = 0, right = A.length - 1 ;
        while (left+1<A.length && A[left] < A[left+1]){
            left++;
        }
        while (right-1 > 0 && A[right] < A[right-1]){
            right--;
        }
        return left > 0 && right < A.length - 1 && left == right;
    }


}
