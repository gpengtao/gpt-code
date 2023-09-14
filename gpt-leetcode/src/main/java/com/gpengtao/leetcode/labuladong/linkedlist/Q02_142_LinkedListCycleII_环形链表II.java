package com.gpengtao.leetcode.labuladong.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.cn/problems/linked-list-cycle-ii/">...</a>
 *
 * @author pengtao.geng on 2023/8/29 20:05.
 */
public class Q02_142_LinkedListCycleII_环形链表II {

	/**
	 * 解法1，用set保存遍历过的节点，如果add时候存在了，则是出现环的位置
	 */
	public ListNode detectCycle(ListNode head) {
		Set<ListNode> nodes = new HashSet<>();
		while (head != null) {
			if (nodes.contains(head)) {
				return head;
			}
			nodes.add(head);
			head = head.next;
		}
		return null;
	}

	/**
	 * 解法2，快慢指针，相遇之后，慢指针回起点，再一起往后走相遇则是环的起点
	 */
	public ListNode detectCycle2(ListNode head) {
		ListNode fast = head;
		ListNode slow = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			if (fast == slow) {
				// 有环break
				break;
			}
		}

		// 无环
		if (fast == null || fast.next == null) {
			return null;
		}

		// slow重新回起点
		slow = head;
		while (fast != slow) {
			fast = fast.next;
			slow = slow.next;
		}
		return slow;
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
