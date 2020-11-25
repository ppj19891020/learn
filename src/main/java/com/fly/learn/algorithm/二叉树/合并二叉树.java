package com.fly.learn.algorithm.二叉树;

import com.fly.learn.algorithm.二叉树.TreeNode;

/**
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 *
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
 *
 * 示例 1:
 *
 * 输入:
 * 	Tree 1                     Tree 2
 *           1                         2
 *          / \                       / \
 *         3   2                     1   3
 *        /                           \   \
 *       5                             4   7
 * 输出:
 * 合并后的树:
 * 	     3
 * 	    / \
 * 	   4   5
 * 	  / \   \
 * 	 5   4   7
 * 注意: 合并必须从两个树的根节点开始。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-binary-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2020/9/23
 * @Description:
 */
public class 合并二叉树 {

    /**
     * t1、t2 都存在，将 t2 的节点值加到 t1 上来。
     * t1 为 null、t2 不是 null，t1 换成 t2。
     * t2 为 null、t1 不是 null，t1 依然 t1。
     * t1 和 t2 都为 null，t1 依然 t1。
     *
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(null == t1 && null == t2){
            return null;
        }
        if(null == t1 && null != t2){
            return t2;
        }
        if(null == t2 && null != t1){
            return t1;
        }
        t1.val = t1.val + t2.val;
        t1.left = mergeTrees(t1.left,t2.left);
        t1.right = mergeTrees(t1.right,t2.right);
        return t1;
    }

}
