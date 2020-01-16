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
        ListNode temp = head;
        while (null != temp){
            System.out.print(temp.val+">");
            temp = temp.next;
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

    /**
     *题目：给定一个单向链表的头结点head,以及两个整数from和to ,在单项链表上把第from个节点和第to个节点这一部分进行反转
     *
     * 列如：
     *  1->2->3->4->5->null,from=2,to=4
     *
     * 结果：1->4->3->2->5->null
     *
     * 列如：
     *
     * 1->2->3->null from=1,to=3
     *
     * 结果为3->2->1->null
     * @param head
     * @param from
     * @param to
     * @return
     */
    public static ListNode partReverseList(ListNode head,int from,int to){
        ListNode headNodePrev = null;//临时node，存储headnode的前一个node，用于修改next指针
        ListNode tailNodePrev = null;//尾结点
        ListNode headNode = null;//头结点
        ListNode tailNode = null;//尾结点

        //定位from-to区间
        ListNode temp = null;
        while (null != head){
            if(head.val == from){
                headNode = head;
                headNodePrev = temp;
            }else if(head.val == to){
                tailNode = head;
                tailNodePrev = tailNode.next;
                break;
            }
            temp = head;
            head = head.next;
        }
//
//        ListNode temp = head.next;//下一个节点
//        head.next = newHead;
//        newHead = head;
//        head = temp;
//
        //部分链表反转
        ListNode newHead = null;
        while (headNode != null && headNode.next != tailNode){
            if(headNode == tailNode){
                //头尾结点一样表示不用反转
                break;
            }
            ListNode temp1 = headNode.next;
            headNode.next = newHead;
            newHead = headNode;
            headNode = temp1;
        }

        headNodePrev.next = newHead;
        tailNode.next = tailNodePrev;
        return head;
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
//        outputLinkList(reverseList2(head));
        partReverseList(head,2,4);
    }

}
