package com.fly.learn.algorithmV2.剑指offer;

import com.codahale.metrics.EWMA;
import com.fly.learn.algorithmV2.二叉树.TreeNode;

/**
 * @author: peijiepang
 * @date 2021/7/6
 * @Description:
 */
public class 二叉搜索树的最近公共祖先 {

    /**
     * 我们从根节点开始遍历；
     *
     * 如果当前节点的值大于 pp 和 qq 的值，说明 pp 和 qq 应该在当前节点的左子树，因此将当前节点移动到它的左子节点；
     *
     * 如果当前节点的值小于 pp 和 qq 的值，说明 pp 和 qq 应该在当前节点的右子树，因此将当前节点移动到它的右子节点；
     *
     * 如果当前节点的值不满足上述两条要求，那么说明当前节点就是「分岔点」。此时，pp 和 qq 要么在当前节点的不同的子树中，要么其中一个就是当前节点。
     *
     *
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof/solution/er-cha-sou-suo-shu-de-zui-jin-gong-gong-0wpw1/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode temp = root;
        while (true){
            if(temp.val > p.val && temp.val > q.val){
                temp = temp.left;
            }else if(temp.val < p.val && temp.val < q.val){
                temp = temp.right;
            }else{
                return temp;
            }
        }
    }

}
