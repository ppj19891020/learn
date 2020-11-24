package com.fly.learn.algorithm.二叉树;

/**
 * 给出一个完全二叉树，求出该树的节点个数。
 *
 * 说明：
 *
 * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 *
 * 示例:
 *
 * 输入:
 *     1
 *    / \
 *   2   3
 *  / \  /
 * 4  5 6
 *
 * 输出: 6
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-complete-tree-nodes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2020/11/24
 * @Description:
 */
public class 完全二叉树的节点个数 {

    /**
     * 层次遍历,获取层级
     * 它是一棵空树或者它的叶子节点只出在最后两层，若最后一层不满则叶子节点只在最左侧。
     * 那么我们来对 root 节点的左右子树进行高度统计，分别记为 left 和 right，有以下两种结果：
     *
     * left == right。这说明，左子树一定是满二叉树，因为节点已经填充到右子树了，左子树必定已经填满了。所以左子树的节点总数我们可以直接得到，是 2^left - 1，加上当前这个 root 节点，则正好是 2^left。再对右子树进行递归统计。
     * left != right。说明此时最后一层不满，但倒数第二层已经满了，可以直接得到右子树的节点个数。同理，右子树节点 +root 节点，总数为 2^right。再对左子树进行递归查找。
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if(null == root){
            return 0;
        }
        int left = countLevel(root.left);
        int right = countLevel(root.right);
        if(left == right){
            return countNodes(root.right) + (1<<left);
        }else{
            return countNodes(root.left) + (1<<right);
        }
    }

    /**
     * 计算层级
     * @param treeNode
     * @return
     */
    public int countLevel(TreeNode treeNode){
        int level = 0;
        while (null != treeNode){
            level++;
            treeNode = treeNode.left;
        }
        return level;
    }

}
