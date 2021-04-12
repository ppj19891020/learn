package com.fly.learn.algorithmV2.二叉树;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 *
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 * 示例 2：
 *
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [0]
 * 输出：[0]
 *  
 *
 * 提示：
 *
 * 树中结点数在范围 [0, 2000] 内
 * -100 <= Node.val <= 100
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2021/4/12
 * @Description:
 */
public class 二叉树展开为链表 {

    /**
     * 常规方法，前序遍历，然后保存在queue中，最后重组node
     * @param root
     */
    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        // 前序遍历
        preorderTraversal(root,list);
        for (int i=1;i<list.size();i++){
            TreeNode prev = list.get(i-1);
            TreeNode curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }

    // 前序遍历
    private void preorderTraversal(TreeNode root, List<TreeNode> list){
        if(null == root){
            return;
        }
        list.add(root);
        preorderTraversal(root.left,list);
        preorderTraversal(root.right,list);
    }

    /**
     * 前序遍历，将右节点压栈保存
     * @param root
     */
    public void flatten1(TreeNode root) {
        if(null == root){
            return;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.push(root);
        TreeNode prev = null;
        while (null != queue.peek()){
            TreeNode curr = queue.poll();
            if(null != prev){
                prev.left = null;
                prev.right = curr;
            }
            TreeNode left = curr.left;
            TreeNode right = curr.right;
            if(null != right){
                queue.push(right);
            }
            if(null != left){
                queue.push(left);
            }
            prev = curr;
        }
    }

}
