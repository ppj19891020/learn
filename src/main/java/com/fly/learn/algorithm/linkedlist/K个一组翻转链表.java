package com.fly.learn.algorithm.linkedlist;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 *  
 *
 * 示例：
 *
 * 给你这个链表：1->2->3->4->5
 *
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 *
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 *
 *  
 *
 * 说明：
 *
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2020/10/21
 * @Description:
 */
public class K个一组翻转链表 {

    public ListNode reverseKGroup(ListNode head, int k) {
        // 头结点
        ListNode temp = new ListNode(0);
        temp.next = head;
        ListNode pre = temp;
        while (null != head) {
            // 尾部
            ListNode tail = pre;
            for (int i = 0; i < k; i++) {
                tail = tail.next;
                if (tail == null) {
                    // 表示不足k个，直接返回
                    return temp.next;
                }
            }

            // 下一组的开头
            ListNode next = tail.next;
            ListNode[] reverse = reverseListNode(head,tail);
            head = reverse[0];
            tail = reverse[1];

            // 重新组装链表
            pre.next = head;
            tail.next = next;

            pre = tail;
            head = tail.next;
        }
        return temp.next;
    }

    /**
     * 反转链表
     */
    public ListNode[] reverseListNode(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode p = head;
        while (prev != tail){
            ListNode nex = p.next;
            p.next = prev;
            prev = p;
            p = nex;
        }
        return new ListNode[]{tail, head};
    }

}
