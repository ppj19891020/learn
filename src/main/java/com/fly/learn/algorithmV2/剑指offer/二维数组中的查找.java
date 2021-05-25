package com.fly.learn.algorithmV2.剑指offer;

/**
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 *  
 *
 * 示例:
 *
 * 现有矩阵 matrix 如下：
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 *
 * 给定 target = 20，返回 false。
 *
 *  
 *
 * 限制：
 *
 * 0 <= n <= 1000
 *
 * 0 <= m <= 1000
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2021/5/25
 * @Description:
 */
public class 二维数组中的查找 {

    public static void main(String[] args) {
        int[][] array = {{5},{6}};
        findNumberIn2DArray(array,6);
    }

    /**
     * 递归+回溯
     * @param matrix
     * @param target
     * @return
     */
    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        int maxRow = matrix.length;// 行
        int maxColume = matrix[0].length; // 列
        int row = 0;
        int column =  maxColume - 1;
        // 从第一行的最后一列开始判断
        // 如果相等直接返回；如果最后一列的值大于目标值，排除最后一列，将判断前一列
        // 如果最后一列的值小于目标值，排除第一行，从下一行开始
        while (row <maxRow && column >=0){
            if (matrix[row][column] == target) {
                return true;
            } else if (matrix[row][column] > target) {
                column--;
            } else {
                row++;
            }
        }
        return false;
    }

}
