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
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        // 进位
        int carry = 0;
        while (null != l1 || null != l2){
            int x = l1 == null?0:l1.val;
            int y = l2 == null?0:l2.val;
            int sum = x + y + carry;
            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            if(null != l1){
                l1 = l1.next;
            }
            if(null != l2){
                l2 = l2.next;
            }
        }
        // 最后一个节点判断是否有进位，如果有进位则需要追加一个节点
        if(1 == carry){
            cur.next = new ListNode(1);
        }

        return pre.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        LinkListUtils.outputLinkList(addTwoNumbers(l1,l2));
    }

}
