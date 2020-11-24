package com.fly.learn.algorithm.二叉树;

import com.fly.learn.algorithm.二叉树.TreeNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回它的 后序 遍历。
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
 * 输出: [3,2,1]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2020/9/29
 * @Description:
 */
public class 二叉树的后序遍历 {

    /**
     * 栈方式
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> queue = new Stack<>();
        TreeNode node = root;
        TreeNode lastVisit = root;//用于保存最近访问
        while (null != node || !queue.isEmpty()){
            while (null != node){
                queue.push(node);
                node = node.left;
            }
            node = queue.peek();
            if(node.right == null || node.right == lastVisit){
                result.add(node.val);
                queue.pop();
                lastVisit = node;
                node = null;
            }else{
                node = node.right;
            }
        }
        return result;
    }

}
