package com.fly.learn.algorithm.linkedlist;

/**
 * leetcode题目：https://leetcode-cn.com/problems/reverse-linked-list/
 * 反转一个单链表。
 * 示例:
 *  输入: 1->2->3->4->5->NULL
 *  输出: 5->4->3->2->1->NULL
 * @author: peijiepang
 * @date 2020-01-03
 * @Description:
 */
public class ReverseLinkList_206 {

    /**
     * 输出linklist
     */
    public static void outputLinkList(ListNode head){
        while (null != head){
            System.out.print(head.val+">");
            head = head.next;
        }
        System.out.println();
    }

    /***
     * 反转链表--头部插入(next 指针反转)
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        if(null == head || head.next == null){
            return head;
        }
        ListNode newHead = null;
        while (null != head){
            ListNode temp = head.next;//下一个节点
            head.next = newHead;
            newHead = head;
            head = temp;
        }
        return newHead;
    }

    public static ListNode reverseList2(ListNode head){
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = null;
        outputLinkList(head);
//        outputLinkList(reverseList(head));
        outputLinkList(reverseList2(head));
    }

}
