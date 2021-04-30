package com.fly.learn.algorithmV2.堆;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * 示例 2：
 *
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 *  
 *
 * 限制：
 *
 * 0 <= k <= arr.length <= 10000
 * 0 <= arr[i] <= 10000
 * 通过次数179,018提交次数314,729
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2021/4/30
 * @Description:
 */
public class 最小的k个数 {

    /**
     * 维护大顶堆
     * @param arr
     * @param k
     * @return
     */
    public static int[] getLeastNumbers(int[] arr, int k) {
        int[] result = new int[k];
        if(k <= 0){
            return result;
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        for (int i=0;i<arr.length;i++){
            if(i < k){
                priorityQueue.offer(arr[i]);
            }else{
                if(priorityQueue.peek() > arr[i]){
                    priorityQueue.poll();
                    priorityQueue.offer(arr[i]);
                }
            }
        }
        for(int i=0;i<k;i++){
            result[i] = priorityQueue.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        int[] temp = new int[]{3,2,1};
        getLeastNumbers(temp,2);
    }

}
