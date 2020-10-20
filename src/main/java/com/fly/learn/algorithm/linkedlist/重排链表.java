package com.fly.learn.algorithm.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例 1:
 *
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 示例 2:
 *
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reorder-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2020/10/20
 * @Description:
 */
public class 重排链表 {

    /**
     * 线性遍历，双指针
     * @param head
     */
    public void reorderList(ListNode head){
        if(null == head){
            return;
        }
        ListNode temp = head;
        List<ListNode> list = new ArrayList<>();
        while (null != temp){
            list.add(temp);
            temp = temp.next;
        }
        // 双指针
        int i = 0, j = list.size() - 1;
        while (i<j){
            list.get(i).next = list.get(j);
            i++;
            if(i ==j){
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }

    /**
     * 1. 找到链表的中间节点
     * 2. 反转链表
     * 3. 合并链表
     * @param head
     */
    public void reorderList1(ListNode head) {
        ListNode mid = middleNode(head);
        ListNode l1 = head;
        ListNode l2 = mid.next;
        mid.next = null;
        l2 = reverseList(l2);
        mergeList(l1, l2);
    }

    /**
     * 快慢指针查找中间节点
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        ListNode fastNode = head;
        ListNode slowNode = head;
        while (fastNode.next != null && slowNode.next.next != null){
            fastNode = fastNode.next;
            slowNode = slowNode.next.next;
        }
        return slowNode;
    }

    /**
     * 反转链表
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (null != cur){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    public void mergeList(ListNode l1, ListNode l2) {
        ListNode l1_tmp;
        ListNode l2_tmp;
        while (l1 != null && l2 != null) {
            l1_tmp = l1.next;
            l2_tmp = l2.next;

            l1.next = l2;
            l1 = l1_tmp;

            l2.next = l1;
            l2 = l2_tmp;
        }
    }

}
