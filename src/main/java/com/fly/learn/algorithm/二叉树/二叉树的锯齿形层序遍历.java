package com.fly.learn.algorithm.二叉树;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层序遍历如下：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2020/12/22
 * @Description:
 */
public class 二叉树的锯齿形层序遍历 {

    /**
     * 层次遍历
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.add(root);
        boolean isLeft = true;
        while (!linkedList.isEmpty()){
            Deque<Integer> levelList = new LinkedList<Integer>();
            int size = linkedList.size();
            for(int i=0;i<size;i++){
                TreeNode treeNode = linkedList.poll();
                if(isLeft){
                    levelList.offerLast(treeNode.val);
                }else{
                    levelList.offerFirst(treeNode.val);
                }
                if(null != treeNode.left){
                    linkedList.add(treeNode.left);
                }
                if(null != treeNode.right){
                    linkedList.add(treeNode.right);
                }
            }
            result.add(new LinkedList<Integer>(levelList));
            isLeft = !isLeft;
        }
        return result;
    }

}
