package com.fly.learn.algorithm.二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 输入:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * 输出: ["1->2->5", "1->3"]
 *
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2020/9/4
 * @Description:
 */
public class 二叉树的所有路径 {

    /**
     * DFS 深度搜索
     * 如果当前节点不是叶子节点，则在当前的路径末尾添加该节点，并继续递归遍历该节点的每一个孩子节点。
     * 如果当前节点是叶子节点，则在当前路径末尾添加该节点后我们就得到了一条从根节点到叶子节点的路径，将该路径加入到答案即可。
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths1(TreeNode root) {
        List<String> paths = new ArrayList<>();
        dfs(root,"",paths);
        return paths;
    }

    private void dfs(TreeNode root,String path,List<String> paths){
        if(null != root){
            StringBuffer pathSB = new StringBuffer(path);
            pathSB.append(root.val);
            if(null == root.left && null == root.right){
                // 叶子节点
                paths.add(pathSB.toString());
            }else{
                // 非叶子节点
                pathSB.append("->");
                dfs(root.left,pathSB.toString(),paths);
                dfs(root.right,pathSB.toString(),paths);
            }
        }
    }

    /**
     * BFS 广度搜索
     * 我们也可以用广度优先搜索来实现。我们维护一个队列，存储节点以及根到该节点的路径。一开始这个队列里只有根节点。
     * 在每一步迭代中，我们取出队列中的首节点，如果它是叶子节点，则将它对应的路径加入到答案中。
     * 如果它不是叶子节点，则将它的所有孩子节点加入到队列的末尾。当队列为空时广度优先搜索结束，我们即能得到答案。
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if(null == root){
            return paths;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<String> pathQueue = new LinkedList<>();
        nodeQueue.offer(root);
        pathQueue.offer(Integer.toString(root.val));
        while (!nodeQueue.isEmpty()){
            TreeNode treeNode = nodeQueue.poll();
            String path = pathQueue.poll();
            if(null == treeNode.left && null == treeNode.right){
                // 叶子节点
                paths.add(path);
            }else{
                if(null != treeNode.left){
                    nodeQueue.add(treeNode.left);
                    pathQueue.add(new StringBuilder(path).append("->").append(treeNode.left.val).toString());
                }
                if(null != treeNode.right){
                    nodeQueue.add(treeNode.right);
                    pathQueue.add(new StringBuilder(path).append("->").append(treeNode.right.val).toString());
                }
            }
        }
        return paths;
    }

    public static void main(String[] args) {
        二叉树的所有路径 test = new 二叉树的所有路径();
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        TreeNode left1Right = new TreeNode(1);
        left.right = left1Right;
//        test.binaryTreePaths1(root);
        test.binaryTreePaths2(root);
    }

}
