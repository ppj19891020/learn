package com.fly.learn.algorithm.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 *
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 *
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2020/10/23
 * @Description:
 */
public class 回文链表 {

    /**
     * 双指针
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if(null ==  head){
            return true;
        }
        List<Integer> list = new ArrayList<>();
        ListNode temp = head;
        while (null != temp){
            list.add(temp.val);
            temp = temp.next;
        }
        //双指针
        int i = 0,j = list.size()-1;
        while (i<j){
            if(list.get(i).equals(list.get(j))){
                i++;
                j--;
            }else {
                return false;
            }
        }
        return true;
    }

    /**
     * 1. 快慢指针找到中间点
     * 2. 后半段直接反转
     * 3. 判断是否相等
     * @param head
     * @return
     */
    public boolean isPalindrome1(ListNode head) {
        if(null == head){
            return true;
        }
        // 找到前半部分链表的尾节点并反转后半部分链表
        ListNode firstHalfEnd = middleNode(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd);

        // 判断是否回文
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 还原链表并返回结果
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    /**
     * 找到中间节点
     * @param head
     * @return
     */
    private ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && slow.next.next != null){
            fast = fast.next;
            slow = slow.next.next;
        }
        return slow;
    }

    /**
     * 反转链表
     * @param head
     * @return
     */
    private ListNode reverseList(ListNode head){
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


}
