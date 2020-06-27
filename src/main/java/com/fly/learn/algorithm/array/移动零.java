package com.fly.learn.algorithm.array;

/**
 //给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 //
 // 示例:
 //
 // 输入: [0,1,0,3,12]
 //输出: [1,3,12,0,0]
 //
 // 说明:
 //
 //
 // 必须在原数组上操作，不能拷贝额外的数组。
 // 尽量减少操作次数。
 //
 // Related Topics 数组 双指针
 //leetcode submit region begin(Prohibit modification and deletion)
 * @author: peijiepang
 * @date 2020/6/27
 * @Description:
 */
public class 移动零 {

    /**
     * 双指针
     * 两次遍历
     * @param nums
     */
    public static void moveZeroes1(int[] nums) {
        System.out.println("输入："+ArrayUtil.print(nums));
        int j = 0 ;
        for(int i=0;i<nums.length;i++){
            if(nums[i] != 0){
                nums[j++] = nums[i];
            }
        }
        for(int i=j;i<nums.length;i++){
            nums[i] = 0;
        }
        System.out.println("输出："+ArrayUtil.print(nums));
    }

    /**
     * 双指针
     * 单次遍历,快排方法，将0的往右，非0的往左
     * @param nums
     */
    public static void moveZeroes2(int[] nums) {
        System.out.println("输入："+ArrayUtil.print(nums));
        int j = 0 ;
        for(int i=0;i<nums.length;i++){
            if(nums[i] != 0){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j++] = temp;
            }
        }
        System.out.println("输出："+ArrayUtil.print(nums));
    }



    public static void main(String[] args) {
        int[] test = new int[]{0,1,0,3,12};
//        moveZeroes1(test);
        moveZeroes2(test);
    }
}
