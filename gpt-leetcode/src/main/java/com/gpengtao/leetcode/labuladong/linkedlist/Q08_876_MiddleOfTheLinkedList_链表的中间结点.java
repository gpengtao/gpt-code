package com.gpengtao.leetcode.labuladong.linkedlist;

import com.gpengtao.leetcode.labuladong.linkedlist.node.ListNode;

/**
 * <a href="https://leetcode.cn/problems/middle-of-the-linked-list/">...</a>
 *
 * @author pengtao.geng on 2023/9/11 19:32.
 */
public class Q08_876_MiddleOfTheLinkedList_链表的中间结点 {

	/**
	 * 解法，先遍历一遍得到链表长度，然后除以2得到中间节点的序号，再遍历到中间的序号即可
	 */
	public ListNode middleNode(ListNode head) {
		ListNode p = head;
		int length = 0;
		while (p != null) {
			p = p.next;
			length++;
		}

		int middle = length / 2;
		for (int i = 0; i < middle; i++) {
			head = head.next;
		}

		return head;
	}
}
