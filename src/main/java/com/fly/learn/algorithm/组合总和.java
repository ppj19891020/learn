package com.fly.learn.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1：
 *
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * 示例 2：
 *
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *  
 *
 * 提示：
 *
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2020/9/9
 * @Description:
 */
public class 组合总和 {

    private int[] candidates;

    /**
     * DFS 回溯法
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.candidates = candidates;
        List<List<Integer>> result = new ArrayList<>();
        // 每种解法
        List<Integer> combine = new ArrayList<>();
        dfs(result,combine,target,0);
        return result;
    }

    private void dfs(List<List<Integer>> result,List<Integer> combine,
        Integer target,int idx){
        // 每次都从后面开始
        if(idx == candidates.length){
            return;
        }
        // 解法
        if(target == 0){
            result.add(new ArrayList<>(combine));
            return;
        }
        // idx每次加1
        dfs(result,combine,target,idx+1);
        if(target >= candidates[idx]){
            combine.add(candidates[idx]);
            dfs(result,combine,target-candidates[idx],idx);
            combine.remove(combine.size()-1);
        }

    }

    public static void main(String[] args) {
        组合总和 test = new 组合总和();
        test.combinationSum(new int[]{2,3,5},8);
    }
}
