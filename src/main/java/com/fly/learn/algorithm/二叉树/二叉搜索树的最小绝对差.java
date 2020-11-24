package com.fly.learn.algorithm.二叉树;

/**
 * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 *
 *  
 *
 * 示例：
 *
 * 输入：
 *
 *    1
 *     \
 *      3
 *     /
 *    2
 *
 * 输出：
 * 1
 *
 * 解释：
 * 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
 *  
 *
 * 提示：
 *
 * 树中至少有 2 个节点。
 * 本题与 783 https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/ 相同
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2020/10/12
 * @Description:
 */
public class 二叉搜索树的最小绝对差 {

    private int result;
    private int pre;

    /**
     * 中序遍历得到递增序列，然后计算相邻的差值即可
     * @param root
     * @return
     */
    public int getMinimumDifference(TreeNode root) {
        result = Integer.MAX_VALUE;
        pre = -1;
        dfs(root);
        return result;
    }

    // 中序遍历
    private void dfs(TreeNode node){
        if(null == node){
            return;
        }
        dfs(node.left);
        if(pre == -1){
            pre = node.val;
        }else{
            result = Math.min(result,node.val-pre);
            pre = node.val;
        }
        dfs(node.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.right = right;
        right.left = left;
        二叉搜索树的最小绝对差 test = new 二叉搜索树的最小绝对差();
        test.getMinimumDifference(root);
    }


}
