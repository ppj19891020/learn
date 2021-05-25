package com.fly.learn.algorithmV2.剑指offer;

/**
 * 题目描述
 * 找出数组中重复的数字。
 *
 * 在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。例如，如果输入长度为 7 的数组 {2, 3, 1, 0, 2, 5, 3}，那么对应的输出是第一个重复的数字 2 或 3 。
 *
 * 测试用例
 * 长度为n的数组包含一个或多个重复的数字。
 * 数组中不包含重复的数字。
 * 无效输入测试用例（输入数组为空；程度为n的数组中包含 0 到 n-1之外的数字）
 * 题目考点
 * 考察应聘者对以为数组的理解及编程能力。一维数组在内存中占据连续的空间，因此我们可以根据下标定位对应的元素。
 * 考察应聘者分析问题的能力，当应聘者发现问题比较复杂的时候，能不能通过具体的例子来找出其中的规律。
 * https://github.com/todorex/Coding-Interviews/blob/master/notes/%E6%95%B0%E7%BB%84%E4%B8%AD%E9%87%8D%E5%A4%8D%E7%9A%84%E6%95%B0%E5%AD%97.md
 * @author: peijiepang
 * @date 2021/5/25
 * @Description:
 */
public class 数组中重复的数字 {

    public static void main(String[] args) {
        int[] array = new int[]{2, 3, 1, 0, 2, 5};
        int[] duplication = new int[array.length];
        findDuplicate(array,duplication);
    }

    /**
     * @param intArray    输入数组
     * @param duplicaiton 将首次找到的重复数字利用duplicaiton[0] = ?存入数组
     * @return 如果输入数组无效返回false，duplicaiton[0]=-1
     * @Description 找出数组中重复的数字
     */
    public static boolean findDuplicate(int[] intArray, int[] duplicaiton) {
        // 杜绝数组为空
        if (intArray.length == 0) {
            duplicaiton[0] = -1;
            return false;
        }
        // 杜绝数组有非法数字
        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] < 0 || intArray[i] > intArray.length - 1) {
                duplicaiton[0] = -1;
                return false;
            }
        }
        for (int i = 0; i < intArray.length; i++) {

            while (intArray[i] != i) {
                if (intArray[i] == intArray[intArray[i]]) {
                    duplicaiton[0] = intArray[i];
                    return true;
                }
                int temp = intArray[i];
                intArray[i] = intArray[temp];
                intArray[temp] = temp;
            }
        }
        duplicaiton[0] = -1;
        return false;
    }

}
