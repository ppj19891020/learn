package com.fly.learn.algorithmV2.排序算法;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * https://visualgo.net/zh/sorting
 * @author: peijiepang
 * @date 2021/5/20
 * @Description:
 */
public class 排序算法 {

    public static void main(String[] args) {
        int[] nums = new int[]{10, 5, 2, 6};
//        冒泡排序(nums);
//        选择排序(nums);
//        插入排序(nums);
//        快排1(nums,0,nums.length-1);
//        快排2(nums,0,nums.length-1);
//        heapSort(nums);
//        计数排序(nums);
        桶排序(nums);
        for(int temp:nums){
            System.out.print(temp + "->");
        }
    }

    //////////////////////////////////////////////////////////---冒泡排序---///////////////////////////////////////////////////////////////////////////

    /**
     * 冒泡排序
     * 每次相邻两个数字进行比较，将大的数字交换到后一个，然后继续操作，一直把本趟最大数字放在最后，完成了一趟，接着对前n-1个数字进行相同的交换操作，将次大的数字放在倒数第二位的位置......一直到所有数字都排列好。
     * 时间复杂度O(n^2)
     * @param nums
     */
    private static void 冒泡排序(int[] nums){
        int length = nums.length;
        boolean flag = true;//用于剪枝，排序好后提前结束
        for(int i=0;i<length && flag;i++){
            flag = false;
            for(int j=length-1;j>i;j--){
                if(nums[j] < nums[j-1]){
                    flag = true;
                    int tmp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = tmp;
                }
            }
        }
    }

    //////////////////////////////////////////////////////////---选择排序---///////////////////////////////////////////////////////////////////////////

    /**
     * 选择排序
     * 首先通过n-1次比较，从n个数字中找到最小的数，将它和第一个数字交换—第一趟选则排序，结果最小的被安置在第一个位置。
     * 再通过n-2次比较，从剩余的n-1个数字找到次小的数，将它与第二个数交换—第二趟选则排序，结果次小的被安置在第二个位置。
     * 重复上述过程......
     * 通过n-1趟排序后完成。
     * @param nums
     */
    private static void 选择排序(int[] nums){
        int length = nums.length;
        for(int i=0;i<length;i++){
            // 找到局部最小值对应索引
            int minIndex = i;
            for(int j=i+1;j<length;j++){
                if(nums[minIndex] > nums[j]){
                    minIndex = j;
                }
            }
            int temp = nums[i];
            nums[i] = nums[minIndex];
            nums[minIndex] = temp;
        }
    }


    //////////////////////////////////////////////////////////---插入排序---///////////////////////////////////////////////////////////////////////////

    /**
     * 插入排序
     * 维护一个已经排好序的数组。每次取一个新的值，找到应该插入的位置，然后把新的值放进去。找位置的时候，是逆序寻找的
     * @param nums
     */
    private static void 插入排序(int[] nums){
        int length = nums.length;
        for(int i=0;i<length-1;i++){
            for(int j=i+1;j>0;j--){
                if(nums[j] < nums[j-1]){
                    int temp = nums[j-1];
                    nums[j-1] = nums[j];
                    nums[j] = temp;
                }else{
                    break;
                }
            }
        }
    }

    //////////////////////////////////////////////////////////---快速排序（双边循环法）---///////////////////////////////////////////////////////////////////////////

    /**
     * 双边循环法
     * @param nums
     * @param left
     * @param right
     */
    private static void 快排1(int[] nums,int left,int right){
        //边界条件
        if(left >= right){
            return;
        }
        int pivioIndex = pivot(nums,left,right);
        快排1(nums,left,pivioIndex-1);
        快排1(nums,pivioIndex+1,right);
    }

    /**
     * 获取基准元素索引
     * 双边-分治法
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private static int pivot(int[] nums,int start,int end){
        int pivot = nums[start]; // 取第一个元素为基准元素
        int left = start;
        int right = end;
        while (left != right){
            // right左移
            while (left<right && pivot < nums[right]){
                right--;
            }
            //left右移
            while (left < right && pivot >= nums[left]){
                left++;
            }
            if(left < right){
                // 交换位置
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }
        nums[start] = nums[left];
        nums[left] = pivot;
        return left;
    }

    //////////////////////////////////////////////////////////---快速排序（单边循环）---///////////////////////////////////////////////////////////////////////////

    /**
     * 单边循环法
     * @param nums
     * @param left
     * @param right
     */
    private static void 快排2(int[] nums,int left,int right){
        //边界条件
        if(left >= right){
            return;
        }
        int pivioIndex = pivot2(nums,left,right);
        快排2(nums,left,pivioIndex-1);
        快排2(nums,pivioIndex+1,right);
    }

    /**
     * 单边循环法
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private static int pivot2(int[] nums,int start,int end) {
        int pivot = nums[start];
        int mark = start;
        for(int i=start+1;i<=end;i++){
            if(nums[i] < pivot){
                mark++;
                int temp = nums[mark];
                nums[mark] = nums[i];
                nums[i] = temp;
            }
        }
        nums[start] = nums[mark];
        nums[mark] = pivot;
        return mark;
    }

    //////////////////////////////////////////////////////////---堆排序---///////////////////////////////////////////////////////////////////////////
    private static void heapSort(int[] array){
        // 1. 把无序数组构建成最大堆
        for(int i = (array.length-2)/2;i >= 0;i--){
            downAdjust(array,i,array.length);
        }
        System.out.println(Arrays.toString(array));
        // 2. 循环删除堆顶元素，移到集合尾部，调整堆产生的堆顶
        for(int i= array.length -1;i > 0;i--){
            // 最后一个元素和第一个元素进行交换
            int temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            // 下层 调整最大堆
            downAdjust(array,0,i);
        }
    }

    /**
     * 下层调整
     * @param array 待调整的堆
     * @param parentIndex 要 下层的父节点
     * @param length 堆的有效大小
     */
    private static void downAdjust(int[] array,int parentIndex,int length){
        // temp 保存父节点值，用于最后赋值
        int temp = array[parentIndex];
        int childIndex = 2 * parentIndex + 1;
        while (childIndex < length){
            // 如果有右孩子，且有孩子的值大于左孩子，则定位到右孩子
            if(childIndex + 1 < length && array[childIndex + 1] > array[childIndex]){
                childIndex++;
            }
            // 如果父节点大于任何一个孩子的值，则直接跳出
            if(temp >= array[childIndex]){
                break;
            }
            // 无需真正交换，单向赋值即可
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * childIndex + 1;
        }
        array[parentIndex] = temp;
    }

    //////////////////////////////////////////////////////////---计数排序---///////////////////////////////////////////////////////////////////////////
    private static int[] 计数排序(int[] nums){
        // 得到最大值和最小值，并计算出差值
        int min = nums[0];
        int max = nums[0];
        for(int i=0;i<nums.length;i++){
            if(min > nums[i]){
                min = nums[i];
            }
            if(max < nums[i]){
                max = nums[i];
            }
        }
        int diff = max - min;

        // 创建统计数组
        int[] countArray = new int[diff+1];
        for(int i=0;i<nums.length;i++){
            countArray[nums[i]-min]++;
        }

        // 统计数组变形，后面的元素值等于前面的元素值
        for(int i=1;i<countArray.length;i++){
            countArray[i] += countArray[i-1];
        }

        //倒序遍历原来的数组，从统计数组中找出正确的位置，并且输出
        int[] sortArray = new int[nums.length];
        for(int i=sortArray.length-1;i>=0;i--){
            sortArray[countArray[nums[i] - min]-1] = nums[i];
            countArray[nums[i] - min]--;
        }
        return sortArray;
    }

    //////////////////////////////////////////////////////////---桶排序---///////////////////////////////////////////////////////////////////////////
    private static int[] 桶排序(int[] nums){
        // 得到最大值和最小值，并计算出差值
        int min = nums[0];
        int max = nums[0];
        for(int i=0;i<nums.length;i++){
            if(min > nums[i]){
                min = nums[i];
            }
            if(max < nums[i]){
                max = nums[i];
            }
        }
        int diff = max - min;

        // 初始化桶
        int bucketNum = nums.length;
        List<LinkedList<Integer>> bucketList = new ArrayList<>();
        for(int i=0;i<bucketNum;i++){
            bucketList.add(new LinkedList<>());
        }

        // 遍历元素值，放入桶中
        // 每个桶的范围： （max-min）/ (bucketNum-1)
        int temp = diff/(bucketNum-1);
        for(int i=0;i<nums.length;i++){
            int num = (nums[i]-min) * (bucketNum-1)/diff;
            bucketList.get(num).add(nums[i]);
        }

        // 对每个桶进行排序
        for(LinkedList<Integer> linkedList:bucketList){
            // jdk底层采用归并排序
            Collections.sort(linkedList);
        }

        // 输出元素
        int[] result = new int[nums.length];
        int index = 0;
        for(LinkedList<Integer> linkedList:bucketList){
            for(Integer value:linkedList){
                result[index] = value;
                index++;
            }
        }
        return result;
    }

}
