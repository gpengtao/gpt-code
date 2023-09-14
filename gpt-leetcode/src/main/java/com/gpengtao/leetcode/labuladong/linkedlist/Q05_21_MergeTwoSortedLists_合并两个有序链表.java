package com.gpengtao.leetcode.labuladong.linkedlist;

import com.gpengtao.leetcode.labuladong.linkedlist.node.ListNode;

import static com.gpengtao.leetcode.labuladong.linkedlist.node.ListNode.buildLinK;
import static com.gpengtao.leetcode.labuladong.linkedlist.node.ListNode.printList;

/**
 * <a href="https://leetcode.cn/problems/merge-two-sorted-lists/">...</a>
 *
 * @author pengtao.geng on 2023/9/7 20:23.
 */
public class Q05_21_MergeTwoSortedLists_合并两个有序链表 {

	/**
	 * 解法1，定义个新的起始head节点，p1、p2遍历两个链表，往head上追
	 */
	public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
		ListNode head = new ListNode(-1);
		ListNode p = head;
		ListNode p1 = list1;
		ListNode p2 = list2;
		while (p1 != null && p2 != null) {
			int v1 = p1.val;
			int v2 = p2.val;
			if (v1 < v2) {
				p.next = p1;
				p1 = p1.next;
			} else {
				p.next = p2;
				p2 = p2.next;
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
		printList(new Q05_21_MergeTwoSortedLists_合并两个有序链表().mergeTwoLists(buildLinK(1, 2, 4), buildLinK(1, 3, 4)));
	}
}
