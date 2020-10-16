package com.fly.learn.algorithm;

/**
 * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 示例 2：
 *
 * 输入：[-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 *  
 *
 * 提示：
 *
 * 1 <= A.length <= 10000
 * -10000 <= A[i] <= 10000
 * A 已按非递减顺序排序。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/squares-of-a-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2020/10/16
 * @Description:
 */
public class 有序数组的平方 {

    /**
     * 双指针法
     * @param A
     * @return
     */
    public int[] sortedSquares(int[] A) {
        int[] result = new int[A.length];
        for(int i=0,j=A.length-1,pos=A.length-1;i<=j;){
            if(A[i]*A[i] > A[j]*A[j]){
                result[pos] = A[i]*A[i];
                ++i;
            }else{
                result[pos] = A[j]*A[j];
                --j;
            }
            --pos;
        }
        return result;
    }

    public static void main(String[] args) {
        有序数组的平方 test = new 有序数组的平方();
        test.sortedSquares(new int[]{-7,-3,2,3,11});
    }

}
