package com.gpengtao.leetcode.linkedlist;

import com.gpengtao.leetcode.linkedlist.node.ListNode;

/**
 * <a href="https://leetcode.cn/problems/remove-nth-node-from-end-of-list/">...</a>
 *
 * @author pengtao.geng on 2023/9/1 09:42.
 */
public class Q04_19_RemoveNthNodeFromEndOfList_删除链表的倒数第N个结点 {

    /**
     * 解法1，先计算链表长度，再算出来正序要删除哪个节点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 计算长度
        ListNode p = head;
        int length = 0;
        while (p != null) {
            p = p.next;
            length++;
        }

        // 特殊处理要删除的是头，返回第二个节点即可
        if (n == length) {
            return head.next;
        }

        // 挪动到要删除的节点的前一个节点
        int step = length - n - 1;
        p = head;
        while (step > 0) {
            p = p.next;
            step--;
        }

        // 删除节点
        p.next = p.next.next;

        return head;
    }

    public static void main(String[] args) {
        Q04_19_RemoveNthNodeFromEndOfList_删除链表的倒数第N个结点 solution = new Q04_19_RemoveNthNodeFromEndOfList_删除链表的倒数第N个结点();

        System.out.println(solution.removeNthFromEnd(ListNode.buildLinK(1, 2), 2));
        System.out.println(solution.removeNthFromEnd(ListNode.buildLinK(1, 2), 1));
        System.out.println(solution.removeNthFromEnd(ListNode.buildLinK(1), 1));
        System.out.println(solution.removeNthFromEnd(ListNode.buildLinK(1, 2, 3, 4, 5), 2));
    }

}
