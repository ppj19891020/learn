package com.fly.learn.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.Test;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * 示例 2：
 *
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2020/11/2
 * @Description:
 */
public class 两个数组的交集 {

    /**
     * map 和 数组
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        int count = 0;
        int[] result = new int[length1+length2];
        Object object = new Object();
        Map<Integer,Object> map = new HashMap<>(Math.min(length1,length2));
        Map<Integer,Object> cache = new HashMap<>(Math.max(length1,length2));

        if(length1 >= length2){
           for(int i=0;i<length2;i++){
               map.put(nums2[i],object);
           }
           for(int i=0;i<length1;i++){
                if(null != map.get(nums1[i]) && null == cache.get(nums1[i])){
                    result[count] = nums1[i];
                    cache.put(nums1[i],object);
                    count++;
                }
           }
        }else{
            for(int i=0;i<length1;i++){
                map.put(nums1[i],object);
            }
            for(int i=0;i<length2;i++){
                if(null != map.get(nums2[i]) && null == cache.get(nums2[i])){
                    result[count] = nums2[i];
                    cache.put(nums2[i],object);
                    count++;
                }
            }
        }
        return Arrays.copyOfRange(result,0,count-1);
    }

}
