package com.fly.learn.algorithmV2.剑指offer;

import com.fly.learn.algorithmV2.二叉树.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
 *
 *  
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回：
 *
 * [3,9,20,15,7]
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2021/6/15
 * @Description:
 */
public class 从上到下打印二叉树 {

    /**
     * 层次遍历
     * @param root
     * @return
     */
    public int[] levelOrder(TreeNode root) {
        if(null == root){
            return new int[]{};
        }
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (null != queue.peek()){
            TreeNode node = queue.poll();
            result.add(node.val);
            if(null != node.left){
                queue.add(node.left);
            }
            if(null != node.right){
                queue.add(node.right);
            }
        }

        int[] d = new int[result.size()];
        for(int i = 0;i<result.size();i++) {
            d[i] = result.get(i);

        }
        return d;
    }

}
