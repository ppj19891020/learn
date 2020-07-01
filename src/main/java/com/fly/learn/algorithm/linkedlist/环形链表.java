package com.fly.learn.algorithm.linkedlist;

import java.util.HashMap;
import java.util.HashSet;

/**
 //给定一个链表，判断链表中是否有环。
 //
 // 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 //
 //
 //
 // 示例 1：
 //
 // 输入：head = [3,2,0,-4], pos = 1
 //输出：true
 //解释：链表中有一个环，其尾部连接到第二个节点。
 //
 //
 //
 //
 // 示例 2：
 //
 // 输入：head = [1,2], pos = 0
 //输出：true
 //解释：链表中有一个环，其尾部连接到第一个节点。
 //
 //
 //
 //
 // 示例 3：
 //
 // 输入：head = [1], pos = -1
 //输出：false
 //解释：链表中没有环。
 //
 //
 //
 //
 //
 //
 // 进阶：
 //
 // 你能用 O(1)（即，常量）内存解决此问题吗？
 // Related Topics 链表 双指针
 * @author: peijiepang
 * @date 2020/6/30
 * @Description:
 */
public class 环形链表 {

    /**
     * 暴力破解法，每次遍历把node放到hash中
     * @param head
     * @return
     */
    public static boolean hasCycle1(ListNode head) {
        HashSet<ListNode> map = new HashSet<>();
        while (null != head){
            if(map.contains(head)){
                return true;
            }
            map.add(head);
            head = head.next;
        }
        return false;
    }

    /**
     * 快慢指针法，慢指针每次走一步，快指针走两步，如果存在有环，则会重合
     * @param head
     * @return
     */
    public static boolean hasCycle2(ListNode head) {
        ListNode first = head;
        ListNode second = head.next.next;
        while ( null != first && null != second){
            if(first == second){
                return true;
            }
            first = first.next;
            second = second.next.next;
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;

//        hasCycle1(node1);
        hasCycle2(node1);

    }
}
