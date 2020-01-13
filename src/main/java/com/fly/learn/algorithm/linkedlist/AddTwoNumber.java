package com.fly.learn.algorithm.linkedlist;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 *
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * @author: peijiepang
 * @date 2020-01-13
 * @Description:
 */
public class AddTwoNumber {

    /**
     * 两数相加:类似数学进位相加
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(null == l1){
            return l2;
        }
        if(null == l2){
            return l1;
        }
        //表示进位
        int jinwei = 0;
        ListNode result =  new ListNode(-1);
        ListNode tmp = result;
        while (null != l1 && null != l2){
            int sum = l1.val + l2.val + jinwei;
            int value = sum % 10;
            result.next = new ListNode(value);
            result = result.next;
            jinwei = sum / 10;
            l1 = l1.next;
            l2 = l2.next;
        }

        //l2不为null l1位null
        while (null != l1){
            int sum = l1.val + jinwei;
            int value = sum % 10 + jinwei;
            result.next = new ListNode(value);
            result = result.next;
            jinwei = sum / 10;
            l1 = l1.next;
        }

        //l2不为null l1位null
        while (null != l2){
            int sum = l2.val + jinwei;
            int value = sum % 10 + jinwei;
            result.next = new ListNode(value);
            result = result.next;
            jinwei = sum / 10;
            l2 = l2.next;
        }

        //最后一个节点主要看是否有没有进位
        if (jinwei == 1) {
            result.next = new ListNode(1);
        }

        return tmp.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        addTwoNumbers(l1,l2);
    }

}
