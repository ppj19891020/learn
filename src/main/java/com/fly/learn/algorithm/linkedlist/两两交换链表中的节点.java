package com.fly.learn.algorithm.linkedlist;

/**
 * lettcode 24题
 //给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 //
 // 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 //
 //
 //
 // 示例:
 //
 // 给定 1->2->3->4, 你应该返回 2->1->4->3.
 //
 // Related Topics 链表
 * @author: peijiepang
 * @date 2020/6/30
 * @Description:
 */
public class 两两交换链表中的节点 {

    public static ListNode swapPairs1(ListNode head) {
        // 定义头节点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prevNode = dummy;
        while ( null != head && null != head.next ){
            ListNode firstNode = head;
            ListNode secondNode = head.next;

            //交换node
            prevNode.next = secondNode;
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;

            //重新赋值head
            prevNode = firstNode;
            head = firstNode.next; // jump
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        LinkListUtils.outputLinkList(node1);
        LinkListUtils.outputLinkList(swapPairs1(node1));

    }

}
