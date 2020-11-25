package com.fly.learn.algorithm.二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 翻转一棵二叉树。
 *
 * 示例：
 *
 * 输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/invert-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2020/9/17
 * @Description:
 */
public class 翻转二叉树 {

    /**
     * 递归
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        //递归函数的终止条件，节点为空时返回
        if(null == root){
            return null;
        }

        // 交换
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // 子数递归
        if(null != root.left){
            invertTree(root.left);
        }
        if(null != root.right){
            invertTree(root.right);
        }
        return root;
    }

    /**
     * DFS实现
     * @param root
     * @return
     */
    public TreeNode invertTree2(TreeNode root) {
        if(root==null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode treeNode = queue.poll();
            TreeNode temp = treeNode.left;
            treeNode.left = treeNode.right;
            treeNode.right = temp;
            if(null != treeNode.left){
                queue.add(treeNode.left);
            }
            if(null != treeNode.right){
                queue.add(treeNode.right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        翻转二叉树 test = new 翻转二叉树();

    }

}
