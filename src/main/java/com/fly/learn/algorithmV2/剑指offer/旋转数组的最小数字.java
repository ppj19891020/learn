package com.fly.learn.algorithmV2.剑指offer;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
 *
 * 示例 1：
 *
 * 输入：[3,4,5,1,2]
 * 输出：1
 * 示例 2：
 *
 * 输入：[2,2,2,0,1]
 * 输出：0
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2021/7/6
 * @Description:
 */
public class 旋转数组的最小数字 {

    /**
     * 二分查找
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        int high = numbers.length - 1;
        int low = 0;
        while (low < high){
            int temp = low + (high-low)/2;
            if(numbers[temp] < numbers[high]){
                high = temp;
            }else if(numbers[temp] > numbers[high]){
                low = temp + 1;
            }else{
                high -= 1;
            }
        }
        return numbers[low];
    }

    public static void main(String[] args) {
        旋转数组的最小数字 test = new 旋转数组的最小数字();
        test.minArray(new int[]{2,2,2,0,1});
    }
}
