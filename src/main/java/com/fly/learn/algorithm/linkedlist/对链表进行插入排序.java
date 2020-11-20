package com.fly.learn.algorithm.linkedlist;

/**
 * 对链表进行插入排序。
 *
 *
 * 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
 * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
 *
 *  
 *
 * 插入排序算法：
 *
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 *  
 *
 * 示例 1：
 *
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2：
 *
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insertion-sort-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2020/11/20
 * @Description:
 */
public class 对链表进行插入排序 {

    /**
     * 双指针
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        if(null == head){
            return head;
        }
        ListNode dumpHead = new ListNode(0);
        dumpHead.next = head;
        // 上次排序的节点
        ListNode lastSorted = head;
        // 当前节点
        ListNode cur = head.next;
        while (null != cur){
            if(lastSorted.val <= cur.val){
                // 当前节点比上个节点大，一直遍历即可
                lastSorted = lastSorted.next;
            }else{
                ListNode pre = dumpHead;
                while (pre.next.val <= cur.val){
                    pre = pre.next;
                }
                lastSorted.next = cur.next;
                cur.next = pre.next;
                pre.next = cur;
            }
            cur = lastSorted.next;
        }
        return dumpHead.next;
    }

}
