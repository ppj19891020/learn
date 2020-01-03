package com.fly.learn.algorithm;

import java.util.Random;

/**
 * 二维数组共n行和n列，数值只有0和1，而且已经按照大小从做往右排序好，求1数值最多的行号是多少？
 * 解题思路：
 *  1. 由于是排序好的，每行只要遍历找到1，后面的数据都是1
 *  2. 按照列遍历，找到第一个1的位置就是该行
 *
 * 比如6行6列如下：
 * 0 0 0 1 1 1
 * 0 0 1 1 1 1
 * 0 1 1 1 1 1
 * 0 0 0 0 1 1
 * 0 1 1 1 1 1
 * 0 0 1 1 1 1
 * @author: peijiepang
 * @date 2020-01-03
 * @Description:
 */
public class 二维数组求最多的数 {

    /**
     * 随机构建二维数组
     * @return
     */
    private static int[][] randomArgument(){
        int n = 0;
        int m = 0;
        while (n < 3){
            n = new Random().nextInt(10);
        }
        while (m < 3){
            m = new Random().nextInt(10);
        }
        System.out.println("构建"+n+"行"+m+"列数组");
        Random random = new Random();
        int[][] result = new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(0!= j && result[i][j-1] == 1){
                    //如果前一列是1的话则后面都是1
                    result[i][j] = 1;
                }else{
                    result[i][j] = random.nextInt(2);
                }
                System.out.print(result[i][j]+" ");
            }
            System.out.println();
        }
        return result;
    }

    /**
     * 求解
     * @param vals
     * @return
     */
     private static int cal(int[][] vals){
        if(vals == null || vals.length == 0 || vals[0].length == 0){
            throw new IllegalArgumentException("非法参数！！！");
        }
        for(int j=0;j<vals[0].length;j++){
            for(int i=0;i<vals.length;i++){
                if(vals[i][j] == 1){
                    System.out.println("i:"+i+" j:"+j);
                    System.out.println("最终结果是第"+(i+1)+"行,1的数量有："+(vals[0].length-j));
                    return j;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[][] result = randomArgument();
        cal(result);
    }

}
