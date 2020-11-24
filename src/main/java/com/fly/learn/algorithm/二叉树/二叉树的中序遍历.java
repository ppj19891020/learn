package com.fly.learn.algorithm.二叉树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,3,2]
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2020/9/14
 * @Description:
 */
public class 二叉树的中序遍历 {

    /**
     * 递归方案
     * 中序：先遍历左节点，在遍历根节点，最后遍历右节点
     * 我们有一棵二叉树：
     *
     *                中
     *               /  \
     *              左   右
     * 前序遍历：中，左，右
     * 中序遍历：左，中，右
     * 后序遍历：左，右，中
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        recursion(root,result);
        return result;
    }

    /**
     * 栈方式
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        //list存储结果的
        List<Integer> list = new ArrayList<>();
        //栈存储结点的
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.empty()) {
            //找当前节点的左子节点，一直找下去，直到为空为止
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            //出栈，这时候root就是最左子节点
            root = stack.pop();
            //然后把最左子节点加入到集合中
            list.add(root.val);
            //最后再访问他的右子节点
            root = root.right;
        }
        return list;
    }

    /**
     * 递归
     * @param treeNode
     * @param result
     */
    private void recursion(TreeNode treeNode,List<Integer> result){
        if(null == treeNode){
            return;
        }
        recursion(treeNode.left,result);
        result.add(treeNode.val);
        recursion(treeNode.right,result);
    }

    public static void main(String[] args) {
        二叉树的中序遍历 test = new 二叉树的中序遍历();
        TreeNode root = new TreeNode(1);
        TreeNode temp1 = new TreeNode(2);
        TreeNode temp2 = new TreeNode(3);
        root.right = temp1;
        temp1.left = temp2;
//        System.out.println(test.inorderTraversal(root));
        System.out.println(test.inorderTraversal2(root));
    }
}
