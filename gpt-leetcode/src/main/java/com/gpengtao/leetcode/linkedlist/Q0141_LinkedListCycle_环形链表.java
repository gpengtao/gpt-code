package com.gpengtao.leetcode.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.cn/problems/linked-list-cycle/">...</a>
 *
 * @author pengtao.geng on 2023/8/29 19:27.
 */
public class Q0141_LinkedListCycle_环形链表 {

	/**
	 * 解法1，用set标记遍历过的节点
	 */
	public boolean hasCycle(ListNode head) {
		Set<ListNode> nodes = new HashSet<>();
		while (head != null) {
			if (nodes.contains(head)) {
				return true;
			}
			nodes.add(head);
			head = head.next;
		}
		return false;
	}

	/**
	 * 解法2，快慢指针
	 */
	public boolean hasCycle2(ListNode head) {
		ListNode fast = head;
		ListNode slow = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			if (fast == slow) {
				return true;
			}
		}
		return false;
	}

	private static class ListNode {
		int val;
		ListNode next;

		ListNode(int val) {
			this.val = val;
			this.next = null;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}
}
