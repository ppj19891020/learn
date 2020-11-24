package com.fly.learn.algorithm.二叉树;

import com.fly.learn.algorithm.二叉树.TreeNode;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author: peijiepang
 * @date 2020/9/24
 * @Description:
 */
public class 二叉树遍历 {

    /**
     * 前序遍历: 根节点 -> 左子数 -> 右子数
     * 递归版本
     * @param root
     */
    public void preOrderTraverse1(TreeNode root) {
        while (null != root){
            System.out.print(root.val+"  ");
            preOrderTraverse1(root.left);
            preOrderTraverse1(root.right);
        }
    }

    /**
     * 前序遍历: 根节点 -> 左子数 -> 右子数
     * 栈版本
     * @param root
     */
    public void preOrderTraverse2(TreeNode root) {
        // 用来暂存节点的栈
        Stack<TreeNode> treeNodeStack = new Stack<TreeNode>();
        // 新建一个游标节点为根节点
        TreeNode node = root;
        // 当遍历到最后一个节点的时候，无论它的左右子树都为空，并且栈也为空
        // 所以，只要不同时满足这两点，都需要进入循环
        while (node != null || !treeNodeStack.isEmpty()) {
            // 若当前考查节点非空，则输出该节点的值
            // 由考查顺序得知，需要一直往左走
            while (node != null) {
                System.out.print(node.val + " ");
                // 为了之后能找到该节点的右子树，暂存该节点
                treeNodeStack.push(node);
                node = node.left;
            }
            // 一直到左子树为空，则开始考虑右子树
            // 如果栈已空，就不需要再考虑
            // 弹出栈顶元素，将游标等于该节点的右子树
            if (!treeNodeStack.isEmpty()) {
                node = treeNodeStack.pop();
                node = node.right;
            }
        }
    }

    /**
     * 中序遍历：左子树 ---> 根结点 ---> 右子树
     * 递归版本
     * @param root
     */
    public void inOrderTraverse1(TreeNode root){
        while (null != root){
            inOrderTraverse1(root.left);
            System.out.println(root.val+" ");
            inOrderTraverse1(root.right);
        }
    }

    /**
     * 中序遍历：左子树 ---> 根结点 ---> 右子树
     * 栈版本
     * @param root
     */
    public void inOrderTraverse2(TreeNode root){
        Stack<TreeNode> treeNodeStack = new Stack<TreeNode>();
        TreeNode node = root;
        while (node != null || !treeNodeStack.isEmpty()) {
            while (node != null) {
                treeNodeStack.push(node);
                node = node.left;
            }
            if (!treeNodeStack.isEmpty()) {
                node = treeNodeStack.pop();
                System.out.print(node.val + " ");
                node = node.right;
            }
        }
    }


    /**
     * 后序遍历：左子树 ---> 右子树 ---> 根结点
     * 递归版本
     * @param root
     */
    public void postOrderTraverse1(TreeNode root){
        while (null != root){
            postOrderTraverse1(root.left);
            System.out.println(root.val+" ");
            postOrderTraverse1(root.right);
        }
    }

    /**
     * 后序遍历：左子树 ---> 右子树 ---> 根结点
     * 栈版本
     * 后序遍历在决定是否可以输出当前节点的值的时候，需要考虑其左右子树是否都已经遍历完成。
     * 所以需要设置一个lastVisit游标。
     * 若lastVisit等于当前考查节点的右子树，表示该节点的左右子树都已经遍历完成，则可以输出当前节点。
     * 并把lastVisit节点设置成当前节点，将当前游标节点node设置为空，下一轮就可以访问栈顶元素。
     * 否者，需要接着考虑右子树，node = node.right。
     *
     * @param root
     */
    public void postOrderTraverse2(TreeNode root){
        Stack<TreeNode> treeNodeStack = new Stack<TreeNode>();
        TreeNode node = root;
        TreeNode lastVisit = root;
        while (node != null || !treeNodeStack.isEmpty()) {
            while (node != null) {
                treeNodeStack.push(node);
                node = node.left;
            }
            //查看当前栈顶元素
            node = treeNodeStack.peek();
            //如果其右子树也为空，或者右子树已经访问
            //则可以直接输出当前节点的值
            if (node.right == null || node.right == lastVisit) {
                System.out.print(node.val + " ");
                treeNodeStack.pop();
                lastVisit = node;
                node = null;
            } else {
                //否则，继续遍历右子树
                node = node.right;
            }
        }
    }

    /**
     * 层次遍历 BFS
     * 只需要一个队列即可，先在队列中加入根结点。之后对于任意一个结点来说，在其出队列的时候，访问之。同时如果左孩子和右孩子有不为空的，入队列。
     * @param root
     */
    public void levelTraverse(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        // offer加到队列的尾部
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            System.out.println(node.val+" ");
            if(null != node.left){
                queue.offer(node.left);
            }
            if(null != node.right){
                queue.offer(node.right);
            }
        }
    }

    /**
     * 深度优先遍历 DFS
     * @param root
     */
    public void depthOrderTraverse(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.push(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            System.out.println(node.val+" ");
            if(null != node.left){
                queue.push(node.left);
            }
            if(null != node.right){
                queue.push(node.right);
            }
        }
    }

}
