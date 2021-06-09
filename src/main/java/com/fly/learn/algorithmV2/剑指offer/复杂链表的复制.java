package com.fly.learn.algorithmV2.剑指offer;

import java.util.HashMap;
import java.util.Map;

/**
 * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 示例 2：
 *
 *
 *
 * 输入：head = [[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 * 示例 3：
 *
 *
 *
 * 输入：head = [[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 * 示例 4：
 *
 * 输入：head = []
 * 输出：[]
 * 解释：给定的链表为空（空指针），因此返回 null。
 *  
 *
 * 提示：
 *
 * -10000 <= Node.val <= 10000
 * Node.random 为空（null）或指向链表中的节点。
 * 节点数目不超过 1000 。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2021/6/8
 * @Description:
 */
public class 复杂链表的复制 {

    /**
     * hash拷贝存储
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        Map<Node,Node> map = new HashMap<>();
        // 构造node映射
        Node temp = head;
        while (null != temp){
            map.put(temp,new Node(temp.val));
            temp = temp.next;
        }
        // 构造新映射
        temp = head;
        Node newNode = new Node(-1);
        Node newHead = newNode;
        while (null != temp){
            Node node1 = map.get(temp);
            node1.next = map.get(temp.next);
            node1.random = map.get(temp.random);
            newHead.next = node1;
            temp = temp.next;
            newHead = newHead.next;
        }
        return newNode.next;
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

}
