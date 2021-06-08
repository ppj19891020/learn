package com.fly.learn.algorithmV2.剑指offer;

import com.fly.learn.algorithm.二叉树.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: peijiepang
 * @date 2021/6/8
 * @Description:
 */
public class 二叉树的深度 {

    /**
     * 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
     *
     * 例如：
     *
     * 给定二叉树 [3,9,20,null,null,15,7]，
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回它的最大深度 3 。
     *
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/er-cha-shu-de-shen-du-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        int result = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (null != queue.peek()){
            Queue<TreeNode> temp = new LinkedList<>();
            for(TreeNode treeNode:queue){
                if(null != treeNode.left){
                    temp.add(treeNode.left);
                }
                if(null != treeNode.right){
                    temp.add(treeNode.right);
                }
            }
            queue = temp;
            result++;
        }
        return result;
    }
}
