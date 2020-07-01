package com.fly.learn.algorithm.linkedlist;

/**
 * @author: peijiepang
 * @date 2020/6/30
 * @Description:
 */
public class LinkListUtils {

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

}
