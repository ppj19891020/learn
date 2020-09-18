package com.fly.learn.algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import sun.security.util.Length;

/**
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 *
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2020/9/18
 * @Description:
 */
public class 全排列II {

    /**
     * DFS 剪支
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        if(nums.length == 0){
            return null;
        }
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        Deque<Integer> path = new ArrayDeque<>();
        dfs(nums,nums.length,0,used,path,result);
        return result;
    }

    private void dfs(int[] nums,int length,int depth,boolean[] used,Deque<Integer> path,List<List<Integer>> result){
        if(length == depth){
            result.add(new ArrayList<>(path));
        }

        for(int i=0;i<length;i++){
           if(used[i]){
               continue;
           }

            // 剪枝条件：i > 0 是为了保证 nums[i - 1] 有意义
            // 写 !used[i - 1] 是因为 nums[i - 1] 在深度优先遍历的过程中刚刚被撤销选择
            if(i>0 && nums[i] == nums[i-1] && !used[i-1]){
                continue;
            }

            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums,length,depth+1,used,path,result);

            // 回溯部分的代码，和 dfs 之前的代码是对称的
            used[i] = false;
            path.removeLast();
        }
    }


}
