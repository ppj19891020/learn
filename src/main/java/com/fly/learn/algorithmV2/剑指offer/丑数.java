package com.fly.learn.algorithmV2.剑指offer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
 *
 *  
 *
 * 示例:
 *
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * 说明:  
 *
 * 1 是丑数。
 * n 不超过1690。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/chou-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2021/6/9
 * @Description:
 */
public class 丑数 {

    /**
     * 大顶堆
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        // 大顶堆
//        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2 - o1;
//            }
//        });

        List<Integer> nthNumber =new ArrayList<>();
        nthNumber.add(2);
        nthNumber.add(3);
        nthNumber.add(5);
        // 维护最小堆，从小到大排序
        PriorityQueue<Long> queue = new PriorityQueue<>();
        queue.add(1L);
        Set<Long> set = new HashSet<>();
        set.add(1L);
        int ugly = 0;
        for(int i=0;i<n;i++){
            long cur = queue.poll();
            ugly = (int)cur;
            for(Integer nth:nthNumber){
                long next = cur*nth;
                if(!set.contains(next)){
                    queue.add(next);
                    set.add(next);
                }
            }
        }
        return ugly;
    }

}
