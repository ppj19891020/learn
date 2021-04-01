package com.fly.learn.algorithm;

/**
 * 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
 *
 * 示例 1 :
 *
 * 输入: 2736
 * 输出: 7236
 * 解释: 交换数字2和数字7。
 * 示例 2 :
 *
 * 输入: 9973
 * 输出: 9973
 * 解释: 不需要交换。
 * 注意:
 *
 * 给定数字的范围是 [0, 108]
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-swap
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2021/4/1
 * @Description:
 */
public class 最大交换 {

    public static void main(String[] args) {
        maximumSwap(1928);
    }

    /**
     * 将数组转为字符串数组方便遍历每一个数字
     * 从后往前遍历，避免1993交换后出现9193而不是9913
     * 用一个数组记录从后往前的每一个数对应的最大值的索引比如1993，从3开始遍历，最大值是3，3的索引是3，遍历到1时，在已经遍历过的元素中最大是9，所以maxArr[0]是9的索引2，最后maxArr =[2,2,2,3]
     * 再从头遍历原数组，比如chars[0] = 1,和他应该对应的最大值chars[maxArr[0]] = chars[2] = 9不相等 则交换
     * 再举个例子98368，maxArr = [0,4,4,4,4],chars[0] = chars[maxArr[0]] = 9 跳过
     * chars[1] = 8, chars[maxArr[1]] = chars[4] = 8 相等 继续跳过
     * chars[2] = 3, chars[maxArr[3]] = chars[4] = 8 不相等，交换
     * 只要交换完成则跳出循环
     * @param num
     * @return
     */
    public static int maximumSwap(int num) {
        char[] temp = String.valueOf(num).toCharArray();
        // 从后往前排序，记录最大值
        int maxId = temp.length-1;
        int[] maxIndex = new int[temp.length];
        for(int i=temp.length-1;i>=0;i--){
            if(temp[i] > temp[maxId]){
                maxId = i;
            }
            maxIndex[i] = maxId;
        }
        for(int i=0;i<temp.length;i++){
            if(temp[i] != temp[maxIndex[i]]){
                char t = temp[maxIndex[i]];
                temp[maxIndex[i]] = temp[i];
                temp[i] = t;
                break;
            }
        }
        return Integer.parseInt(new String(temp));
    }

}
