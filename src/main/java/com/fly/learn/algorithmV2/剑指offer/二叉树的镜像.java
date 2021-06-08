package com.fly.learn.algorithmV2.剑指offer;

import com.fly.learn.algorithm.二叉树.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 *
 * 例如输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 镜像输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 *  
 *
 * 示例 1：
 *
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 *  
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 1000
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2021/6/8
 * @Description:
 */
public class 二叉树的镜像 {

    /**
     * 层次遍历
     * @param root
     * @return
     */
    public TreeNode mirrorTree(TreeNode root) {
        // 层次遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (null != queue.peek()){
            TreeNode node = queue.poll();
            if(null != node.left || null != node.right){
                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;
            }
            if(null != node.left){
                queue.add(node.left);
            }
            if(null != node.right){
                queue.add(node.right);
            }
        }
        return root;
    }

}
