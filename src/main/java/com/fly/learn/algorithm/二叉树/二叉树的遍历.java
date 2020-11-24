package com.fly.learn.algorithm.二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其自底向上的层次遍历为：
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2020/9/6
 * @Description:
 */
public class 二叉树的遍历 {

    /**
     * 广度优先遍历
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        if(null ==  root){
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()){
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            for(int i=0;i<size;i++){
                TreeNode node = queue.poll();
                level.add(node.val);
                if(null != node.left){
                    queue.add(node.left);
                }
                if(null !=  node.right){
                    queue.add(node.right);
                }
            }
            // 每次加到头结点
            result.add(0,level);
        }
        return result;
    }

    public static void main(String[] args) {
        二叉树的遍历 test = new 二叉树的遍历();
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        TreeNode left1Right = new TreeNode(1);
        left.right = left1Right;
        test.levelOrderBottom(root);
    }

}
