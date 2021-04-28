package com.fly.learn.algorithmV2.链表;

/**
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 进阶：你能尝试使用一趟扫描实现吗？
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 示例 2：
 *
 * 输入：head = [1], n = 1
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 *  
 *
 * 提示：
 *
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2021/4/27
 * @Description:
 */
public class 删除链表倒数第N个节点 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode result = new ListNode();
        result.next = head;
        ListNode pre = null;
        ListNode cur = result;
        int i = 1; //索引
        while (null != head){
            if(i >= n){
                pre = cur;
                cur = cur.next;
            }
            head = head.next;
            i++;
        }
        pre.next = pre.next.next;
        return result.next;
    }

}
