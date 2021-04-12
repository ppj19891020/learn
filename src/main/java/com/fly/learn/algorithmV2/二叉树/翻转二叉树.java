package com.fly.learn.algorithmV2.二叉树;

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
 * 备注:
 * 这个问题是受到 Max Howell 的 原问题 启发的 ：
 *
 * 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/invert-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2021/4/2
 * @Description:
 */
public class 翻转二叉树 {

    /**
     * 按照层级遍历，然后替换
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if(null == root){
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (null != queue.peek()){
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

    /**
     * 前序递归遍历
     * @param root
     * @return
     */
    public TreeNode invertTree1(TreeNode root) {
        if(null == root){
            return null;
        }
        // 前序遍历直接交换它的左右子节点
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertTree1(root.left);
        invertTree1(root.right);
        return root;
    }

}
