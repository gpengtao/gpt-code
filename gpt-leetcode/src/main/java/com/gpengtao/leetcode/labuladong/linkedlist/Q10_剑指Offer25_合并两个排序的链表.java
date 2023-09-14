package com.gpengtao.leetcode.labuladong.linkedlist;

import com.gpengtao.leetcode.labuladong.linkedlist.node.ListNode;

import static com.gpengtao.leetcode.labuladong.linkedlist.node.ListNode.buildLinK;
import static com.gpengtao.leetcode.labuladong.linkedlist.node.ListNode.printList;

/**
 * <a href="https://leetcode.cn/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/">...</a>
 *
 * @author pengtao.geng on 2023/9/14 10:08.
 * @see Q05_21_MergeTwoSortedLists_合并两个有序链表
 */
public class Q10_剑指Offer25_合并两个排序的链表 {

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode head = new ListNode(-1);
		ListNode p = head;
		ListNode p1 = l1;
		ListNode p2 = l2;
		while (p1 != null && p2 != null) {
			if (p1.val > p2.val) {
				p.next = p2;
				p2 = p2.next;
			} else {
				p.next = p1;
				p1 = p1.next;
			}
			p = p.next;
		}
		if (p1 == null) {
			p.next = p2;
		}
		if (p2 == null) {
			p.next = p1;
		}
		return head.next;
	}

	public static void main(String[] args) {
		printList(new Q10_剑指Offer25_合并两个排序的链表().mergeTwoLists(buildLinK(1, 2, 4), buildLinK(1, 3, 4)));
	}
}
